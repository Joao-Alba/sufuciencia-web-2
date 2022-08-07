package br.furb.restaurante.entity.dto;

import br.furb.restaurante.entity.Comanda;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ComandaDto {

    private UsuarioDto usuario;
    private List<ProdutoDto> produtoList;

    public ComandaDto(Comanda comanda){
        this.usuario = new UsuarioDto(comanda.getUsuario());
        produtoList.addAll(comanda.getProdutoList().stream().map(ProdutoDto::new).collect(Collectors.toList()));
    }
}
