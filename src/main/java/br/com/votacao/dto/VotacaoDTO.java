package br.com.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/* Detalhes da votação (admin cria votacao com tipo, maximo escolhas e opcoes) */
@Data
@AllArgsConstructor
public class VotacaoDTO {
    private String titulo;
    private String tipo; // SIMPLES, MULTIPLA, ELEICAO
    private int maximoEscolhas;
    private List<String> opcoes; // apenas as descrições
}
