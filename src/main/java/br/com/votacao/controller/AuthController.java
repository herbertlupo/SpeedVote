package br.com.votacao.controller;

import br.com.votacao.dto.LoginRequestDTO;
import br.com.votacao.dto.LoginResponseDTO;
import br.com.votacao.dto.RegisterDTO;
import br.com.votacao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return userService.autenticar(request);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {
        try {
            userService.registrar(dto);
            return ResponseEntity.ok("Usuário registrado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
