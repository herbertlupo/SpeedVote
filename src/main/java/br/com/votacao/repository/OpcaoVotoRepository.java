package br.com.votacao.repository;

import br.com.votacao.model.OpcaoVoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcaoVotoRepository extends JpaRepository<OpcaoVoto, Long> {
}
