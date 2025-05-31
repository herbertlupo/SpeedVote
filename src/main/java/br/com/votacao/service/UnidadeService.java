package br.com.votacao.service;

import br.com.votacao.model.Unidade;
import br.com.votacao.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<Unidade> listarTodas() {
        return unidadeRepository.findAll();
    }

    public Optional<Unidade> buscarPorId(Long id) {
        return unidadeRepository.findById(id);
    }

    public Unidade salvar(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }

    public void deletar(Long id) {
        unidadeRepository.deleteById(id);
    }
}
