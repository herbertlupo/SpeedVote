package br.com.votacao.dto;

import br.com.votacao.model.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDTO {
    private Long id;
    private String nome;
    private String username;
    private String senha;
    private TipoUsuario tipo;
}
