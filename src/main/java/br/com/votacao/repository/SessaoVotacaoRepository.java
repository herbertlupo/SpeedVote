package br.com.votacao.repository;

import br.com.votacao.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
}
