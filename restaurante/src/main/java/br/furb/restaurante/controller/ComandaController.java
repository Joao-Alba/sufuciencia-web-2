package br.furb.restaurante.controller;

import br.furb.restaurante.entity.Comanda;
import br.furb.restaurante.entity.Produto;
import br.furb.restaurante.entity.dto.ComandaDto;
import br.furb.restaurante.entity.dto.ComandaFullDto;
import br.furb.restaurante.service.ComandaService;
import br.furb.restaurante.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/RestAPIFurb/comandas")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ComandaDto>> getComandas() {
        List<Comanda> comandaList = comandaService.getAllComandas();

        if (!comandaList.isEmpty()) {
            return ResponseEntity.ok(comandaList.stream().map(ComandaDto::new).collect(Collectors.toList()));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ComandaFullDto> getComandaById(@PathVariable Long id) {
        Comanda comanda = comandaService.getComandaById(id);

        if (Objects.nonNull(comanda)) {
            return ResponseEntity.ok(new ComandaFullDto(comanda));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Comanda> saveComanda(@RequestBody Comanda comanda) {
        try {

            comanda.getProdutos().forEach(produto -> {
                if (Objects.nonNull(produtoService.getProdutoById(produto.getId()))) {
                    throw new DataIntegrityViolationException("Produto com id duplicado.");
                }
            });

            Comanda comandaSalva = comandaService.saveComanda(comanda);
            return new ResponseEntity<>(comandaSalva, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.getMessage());
        }
    }

    @PutMapping("{comandaId}")
    public ResponseEntity<ComandaFullDto> updateComanda(@PathVariable Long comandaId, @RequestBody Comanda comandaNova) {

        try {
            Comanda comanda = comandaService.getComandaById(comandaId);

            if (Objects.isNull(comanda)) {
                return ResponseEntity.noContent().build();
            }

            comanda.setNomeUsuario(Objects.nonNull(comandaNova.getNomeUsuario()) ? comandaNova.getNomeUsuario() : comanda.getNomeUsuario());
            comanda.setTelefoneUsuario(Objects.nonNull(comandaNova.getTelefoneUsuario()) ? comandaNova.getTelefoneUsuario() : comanda.getTelefoneUsuario());

            comandaNova.getProdutos().forEach(produto -> {
                if (comanda.getProdutos().stream().noneMatch(produtoBase -> produtoBase.getId().equals(produto.getId()))) {

                    if (Objects.nonNull(produtoService.getProdutoById(produto.getId()))) {
                        throw new DataIntegrityViolationException("Produto com id duplicado");
                    }

                    produtoService.saveProduto(produto);
                    comanda.getProdutos().add(produto);
                } else {
                    Produto produtoAntigo = comanda.getProdutos().stream().filter(produtoBase -> produtoBase.getId().equals(produto.getId())).findFirst().get();
                    produtoAntigo.setNome(produto.getNome());
                    produtoAntigo.setPreco(produto.getPreco());
                }
            });

            Comanda comandaSalva = comandaService.saveComanda(comanda);
            return new ResponseEntity<>(new ComandaFullDto(comandaSalva), HttpStatus.OK);
        } catch (DataIntegrityViolationException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComanda(@PathVariable Long id) {
        if (comandaService.deleteComanda(id)) {
            return ResponseEntity.ok("success{comanda removida");
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
