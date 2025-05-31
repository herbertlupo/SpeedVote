package br.com.votacao.repository;

import br.com.votacao.model.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondominioRepository extends JpaRepository<Condominio, Long> {
}
