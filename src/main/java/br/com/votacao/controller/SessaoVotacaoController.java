package br.com.votacao.controller;

import br.com.votacao.dto.SessaoVotacaoDTO;
import br.com.votacao.model.SessaoVotacao;
import br.com.votacao.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoVotacaoController {

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @GetMapping
    public List<SessaoVotacaoDTO> listarTodas() {
        return sessaoVotacaoService.toDTOList(sessaoVotacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public SessaoVotacaoDTO buscarPorId(@PathVariable Long id) {
        return sessaoVotacaoService.buscarPorId(id)
                .map(sessaoVotacaoService::toDTO)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));
    }

    @PostMapping
    public SessaoVotacaoDTO criar(@RequestBody SessaoVotacaoDTO dto) {
        SessaoVotacao salvo = sessaoVotacaoService.salvar(sessaoVotacaoService.toEntity(dto));
        return sessaoVotacaoService.toDTO(salvo);
    }
}
