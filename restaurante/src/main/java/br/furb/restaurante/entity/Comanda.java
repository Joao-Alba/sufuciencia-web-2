package br.furb.restaurante.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comandas")
@Data
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private String nomeUsuario;

    @Column(nullable = false)
    private String telefoneUsuario;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;
}
