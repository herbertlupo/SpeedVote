package br.com.votacao.service;

import br.com.votacao.model.Condominio;
import br.com.votacao.repository.CondominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondominioService {

    @Autowired
    private CondominioRepository condominioRepository;

    public List<Condominio> listarTodos() {
        return condominioRepository.findAll();
    }

    public Optional<Condominio> buscarPorId(Long id) {
        return condominioRepository.findById(id);
    }

    public Condominio salvar(Condominio condominio) {
        return condominioRepository.save(condominio);
    }

    public void deletar(Long id) {
        condominioRepository.deleteById(id);
    }
}
