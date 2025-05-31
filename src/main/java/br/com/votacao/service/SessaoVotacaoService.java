package br.com.votacao.service;

import br.com.votacao.dto.SessaoVotacaoDTO;
import br.com.votacao.model.SessaoVotacao;
import br.com.votacao.repository.SessaoVotacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessaoVotacaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SessaoVotacao salvar(SessaoVotacao sessao) {
        return sessaoVotacaoRepository.save(sessao);
    }

    public List<SessaoVotacao> listarTodas() {
        return sessaoVotacaoRepository.findAll();
    }

    public Optional<SessaoVotacao> buscarPorId(Long id) {
        return sessaoVotacaoRepository.findById(id);
    }

    // Conversões

    public SessaoVotacaoDTO toDTO(SessaoVotacao sessao) {
        return modelMapper.map(sessao, SessaoVotacaoDTO.class);
    }

    public SessaoVotacao toEntity(SessaoVotacaoDTO dto) {
        return modelMapper.map(dto, SessaoVotacao.class);
    }

    public List<SessaoVotacaoDTO> toDTOList(List<SessaoVotacao> lista) {
        return lista.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
