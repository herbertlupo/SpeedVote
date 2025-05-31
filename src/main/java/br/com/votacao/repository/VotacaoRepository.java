package br.com.votacao.repository;

import br.com.votacao.model.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {
    Optional<Votacao> findBySessaoId(Long sessionId);
}
