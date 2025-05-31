package br.com.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/* retorno simples mensagem e sucesso */
@Data
@AllArgsConstructor
public class VotoResponseDTO {
    private boolean sucesso;
    private String mensagem;
}
