package br.furb.restaurante.entity.dto;

import br.furb.restaurante.entity.Produto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDto {

    private Long id;
    private String nome;
    private Double preco;

    public ProdutoDto(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
}
