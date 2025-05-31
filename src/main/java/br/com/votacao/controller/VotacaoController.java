package br.com.votacao.controller;

import br.com.votacao.dto.VotacaoDTO;
import br.com.votacao.model.Votacao;
import br.com.votacao.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Apenas para criar ou consultar votos via admin */
@RestController
@RequestMapping("/api/admin/votos")
public class VotacaoController {

    @Autowired
    private VotacaoService votacaoService;

    @GetMapping
    public List<VotacaoDTO> listarTodas() {
        return votacaoService.toDTOList(votacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public VotacaoDTO buscarPorId(@PathVariable Long id) {
        return votacaoService.buscarPorId(id)
                .map(votacaoService::toDTO)
                .orElseThrow(() -> new RuntimeException("Votação não encontrada"));
    }

    @PostMapping
    public VotacaoDTO criar(@RequestBody VotacaoDTO dto) {
        Votacao salvo = votacaoService.salvar(votacaoService.toEntity(dto));
        return votacaoService.toDTO(salvo);
    }
}
