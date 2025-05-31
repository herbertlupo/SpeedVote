package br.com.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ResultadoResponseDTO {
    private String tituloVotacao;
    private Map<String, Double> percentualPorOpcao;
    private int totalVotos;

    public ResultadoResponseDTO(){}
}

