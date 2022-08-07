package br.furb.restaurante.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
@Data
public class Produto {

    @Id
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;
}
