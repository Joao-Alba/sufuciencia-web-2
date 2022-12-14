package br.furb.restaurante.entity.dto;

import br.furb.restaurante.entity.Comanda;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComandaDto {

    private Long idUsuario;
    private String nomeUsuario;
    private String telefoneUsuario;

    public ComandaDto(Comanda comanda){
        this.idUsuario = comanda.getIdUsuario();
        this.nomeUsuario = comanda.getNomeUsuario();
        this.telefoneUsuario = comanda.getTelefoneUsuario();
    }
}
