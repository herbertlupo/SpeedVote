package br.com.votacao.repository;

import br.com.votacao.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
}
