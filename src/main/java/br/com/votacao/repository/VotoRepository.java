package br.com.votacao.repository;

import br.com.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findByVotacaoId(Long votacaoId);
}
