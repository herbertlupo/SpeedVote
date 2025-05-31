package br.com.votacao.service;

import br.com.votacao.dto.ResultadoResponseDTO;
import br.com.votacao.model.Votacao;
import br.com.votacao.model.Voto;
import br.com.votacao.repository.OpcaoVotoRepository;
import br.com.votacao.repository.VotacaoRepository;
import br.com.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApuracaoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private OpcaoVotoRepository opcaoVotoRepository;

    @Autowired
    private VotacaoRepository votacaoRepository;

    public ResultadoResponseDTO apurarPorSessao(Long sessaoId) {
        Optional<Votacao> votacaoOpt = votacaoRepository.findBySessaoId(sessaoId);
        if (votacaoOpt.isEmpty()) {
            throw new RuntimeException("Sessão não encontrada");
        }
        Votacao votacao = votacaoOpt.get();
        return apurarPorVotacao(votacao.getId());
    }

    public ResultadoResponseDTO apurarPorVotacao(Long idVotacao) {
        List<Voto> votos = votoRepository.findAll();

        Map<String, Double> somaPesosPorOpcao = new HashMap<>();
        double totalPesos = 0.0;
        int totalVotos = 0;
        String tituloVotacao = "";

        for (Voto voto : votos) {
            if (!voto.getVotacao().getId().equals(idVotacao)) continue;

            if (tituloVotacao.isEmpty()) {
                tituloVotacao = voto.getVotacao().getTitulo();
            }

            totalVotos++;
            totalPesos += voto.getPesoComputado();

            String[] opcoes = voto.getOpcoesSelecionadas().split(",");

            for (String idOpcaoStr : opcoes) {
                Long idOpcao = Long.parseLong(idOpcaoStr.trim());

                opcaoVotoRepository.findById(idOpcao).ifPresent(opcao -> {
                    String desc = opcao.getDescricao();
                    double peso = somaPesosPorOpcao.getOrDefault(desc, 0.0);
                    somaPesosPorOpcao.put(desc, peso + voto.getPesoComputado());
                });
            }
        }
        final double total = totalPesos;

        // Converte soma de pesos em percentual por opção
        Map<String, Double> percentualPorOpcao = somaPesosPorOpcao.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Math.round((e.getValue() / total) * 10000.0) / 100.0 // duas casas decimais
                ));

        return new ResultadoResponseDTO(tituloVotacao, percentualPorOpcao, totalVotos);
    }
}

