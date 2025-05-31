package br.com.votacao.repository;

import br.com.votacao.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Optional<Participante> findByCpf(String cpf);
    Optional<Participante> findByTokenVotacao(String tokenVotacao);
    Optional<Participante> findByTelefoneAndSessaoId(String telefone, Long sessaoId);

}
