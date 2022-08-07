package br.furb.restaurante.entity.dto;

import br.furb.restaurante.entity.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {

    private Long id;
    private Long idUsuario;
    private String nomeUsuario;
    private String telefoneUsuario;

    public UsuarioDto(Usuario usuario){
        this.id = usuario.getId();
        this.idUsuario = usuario.getIdUsuario();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.telefoneUsuario = usuario.getTelefoneUsuario();
    }
}
