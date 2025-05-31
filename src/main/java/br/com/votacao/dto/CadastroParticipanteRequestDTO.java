package br.com.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CadastroParticipanteRequestDTO {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private boolean representaJuridico;
    private Long sessaoId;
}