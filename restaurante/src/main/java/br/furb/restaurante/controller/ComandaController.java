package br.furb.restaurante.controller;

import br.furb.restaurante.entity.Comanda;
import br.furb.restaurante.entity.dto.ComandaDto;
import br.furb.restaurante.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comandas")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    @GetMapping
    public ResponseEntity<List<ComandaDto>> getComandas(){
        List<Comanda> comandaList = comandaService.getAllComandas();

        if(!comandaList.isEmpty()){
            return ResponseEntity.ok(comandaList.stream().map(ComandaDto::new).collect(Collectors.toList()));
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ComandaDto> getComandaById(@PathVariable Long id){
        Comanda comanda = comandaService.getComandaById(id);

        if(Objects.nonNull(comanda)){
            return ResponseEntity.ok(new ComandaDto(comanda));
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Comanda> saveComanda(@RequestBody Comanda comanda){
        try {
            Comanda comandaSalva = comandaService.saveComanda(comanda);
            return ResponseEntity.ok(comandaSalva);
        } catch (DataIntegrityViolationException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteComanda(@PathVariable Long id){
        if(comandaService.deleteComanda(id)){
            return ResponseEntity.ok("comanda removida");
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
