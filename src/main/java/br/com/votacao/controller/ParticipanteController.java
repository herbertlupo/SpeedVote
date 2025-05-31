package br.com.votacao.controller;

import br.com.votacao.dto.CadastroParticipanteRequestDTO;
import br.com.votacao.dto.CadastroParticipanteResponseDTO;
import br.com.votacao.model.Participante;
import br.com.votacao.model.SessaoVotacao;
import br.com.votacao.repository.ParticipanteRepository;
import br.com.votacao.repository.SessaoVotacaoRepository;
import br.com.votacao.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoRepository;

    //retorna todos os participantes
    @GetMapping
    public List<CadastroParticipanteRequestDTO> listarTodos() {
        return participanteService.toDTOList(participanteService.listarTodos());
    }

    //busca participante por ID
    @GetMapping("/{id}")
    public CadastroParticipanteRequestDTO buscarPorId(@PathVariable Long id) {
        return participanteService.buscarPorId(id)
                .map(participanteService::toDTO)
                .orElseThrow(() -> new RuntimeException("Participante não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        participanteService.remover(id);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<CadastroParticipanteResponseDTO> cadastrarParticipante(@RequestBody CadastroParticipanteRequestDTO dto) {
        Optional<SessaoVotacao> sessaoOpt = sessaoRepository.findById(dto.getSessaoId());
        if (sessaoOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Verifica se já existe participante com esse telefone nessa sessão
        Optional<Participante> existente = participanteRepository.findByTelefoneAndSessaoId(dto.getTelefone(), dto.getSessaoId());
        if (existente.isPresent()) {
            return ResponseEntity.ok(new CadastroParticipanteResponseDTO(existente.get().getTokenVotacao()));
        }

        Participante p = new Participante();
        p.setNome(dto.getNome());
        p.setTelefone(dto.getTelefone());
        p.setRepresentaJuridico(dto.isRepresentaJuridico());
        p.setSessao(sessaoOpt.get());
        p.setTokenVotacao(UUID.randomUUID().toString());

        participanteRepository.save(p);

        return ResponseEntity.ok(new CadastroParticipanteResponseDTO(p.getTokenVotacao()));
    }

    @PostMapping("/token/recuperar")
    public ResponseEntity<CadastroParticipanteResponseDTO> recuperarToken(
            @RequestParam String telefone,
            @RequestParam Long sessaoId) {

        Optional<Participante> participanteOpt = participanteRepository.findByTelefoneAndSessaoId(telefone, sessaoId);

        if (participanteOpt.isPresent()) {
            return ResponseEntity.ok(new CadastroParticipanteResponseDTO(participanteOpt.get().getTokenVotacao()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
