package br.com.votacao.service;

import br.com.votacao.dto.LoginRequestDTO;
import br.com.votacao.dto.LoginResponseDTO;
import br.com.votacao.dto.RegisterDTO;
import br.com.votacao.model.User;
import br.com.votacao.repository.UserRepository;
import br.com.votacao.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponseDTO autenticar(LoginRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getSenha(), user.getSenha())) {
            throw new BadCredentialsException("Senha inválida");
        }

        String token = jwtUtil.generateToken(user);
        return new LoginResponseDTO(token, user.getTipo().name());
    }

    public Optional<User> buscarPorUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User salvar(User user) {
        return userRepository.save(user);
    }

    public User registrar(RegisterDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username já está em uso");
        }

        User user = new User();
        user.setNome(dto.getNome());
        user.setUsername(dto.getUsername());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));
        user.setTipo(dto.getTipo());

        return userRepository.save(user);
    }



}
