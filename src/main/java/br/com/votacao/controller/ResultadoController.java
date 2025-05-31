package br.com.votacao.controller;

import br.com.votacao.dto.ResultadoResponseDTO;
import br.com.votacao.service.ApuracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    @Autowired
    private ApuracaoService apuracaoService;

    @GetMapping("/{sessaoId}")
    public ResponseEntity<ResultadoResponseDTO> obterResultado(@PathVariable Long sessaoId) {
        ResultadoResponseDTO resultado = apuracaoService.apurarPorSessao(sessaoId);
        return ResponseEntity.ok(resultado);
    }
}
