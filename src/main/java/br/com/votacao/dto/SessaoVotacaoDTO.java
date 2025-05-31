package br.com.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SessaoVotacaoDTO {
    private String titulo;
    private Date dataInicio;
    private Date dataFim;
    private boolean votacaoLiberada;
}
