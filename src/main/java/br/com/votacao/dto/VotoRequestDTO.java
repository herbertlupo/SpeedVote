package br.com.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/* usado pelo publico para votar com token */
@Data
@AllArgsConstructor
public class VotoRequestDTO {
    private String tokenVotacao;
    private Long votacaoId;
    private String[] opcoesSelecionadas; // Pode ser 1 ou várias dependendo da votação
}
