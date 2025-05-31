package br.com.votacao.controller;

import br.com.votacao.dto.VotoRequestDTO;
import br.com.votacao.dto.VotoResponseDTO;
import br.com.votacao.model.Participante;
import br.com.votacao.model.SessaoVotacao;
import br.com.votacao.model.Votacao;
import br.com.votacao.model.Voto;
import br.com.votacao.repository.ParticipanteRepository;
import br.com.votacao.repository.VotacaoRepository;
import br.com.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/voto")
public class VotacaoPublicaController {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private VotacaoRepository votacaoRepository;

    @PostMapping
    public ResponseEntity<VotoResponseDTO> votar(@RequestBody VotoRequestDTO dto) {
        Optional<Participante> participanteOpt = participanteRepository.findByTokenVotacao(dto.getTokenVotacao());

        if (participanteOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new VotoResponseDTO(false,"Token inválido"));
        }

        Participante participante = participanteOpt.get();
        SessaoVotacao sessao = participante.getSessao();

        if (!sessao.getStatus().name().equals("ABERTA")) {
            return ResponseEntity.badRequest().body(new VotoResponseDTO(false,"Votação encerrada"));
        }

        Votacao votacao = votacaoRepository.findById(dto.getVotacaoId())
                .orElseThrow(() -> new RuntimeException("Votação não encontrada"));

        if (!votacao.getSessao().getId().equals(sessao.getId())) {
            throw new RuntimeException("Votação não pertence à sessão do participante");
        }

        Voto voto = new Voto();
        voto.setParticipante(participante);
        voto.setVotacao(votacao);
        voto.setOpcoesSelecionadas(String.join(",",dto.getOpcoesSelecionadas()));
        voto.setPesoComputado(participante.getPesoComputado());

        votoRepository.save(voto);

        return ResponseEntity.ok(new VotoResponseDTO(true,"Voto computado com sucesso"));
    }
}
