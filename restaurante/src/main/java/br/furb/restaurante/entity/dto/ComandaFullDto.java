package br.furb.restaurante.entity.dto;

import br.furb.restaurante.entity.Comanda;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ComandaFullDto {

    private Long idUsuario;
    private String nomeUsuario;
    private String telefoneUsuario;
    private List<ProdutoDto> produtos = new ArrayList<>();

    public ComandaFullDto(Comanda comanda){
        this.idUsuario = comanda.getIdUsuario();
        this.nomeUsuario = comanda.getNomeUsuario();
        this.telefoneUsuario = comanda.getTelefoneUsuario();
        this.produtos.addAll(comanda.getProdutos().stream().map(ProdutoDto::new).collect(Collectors.toList()));
    }
}
