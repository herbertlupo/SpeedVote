package br.com.votacao.service;

import br.com.votacao.dto.VotacaoDTO;
import br.com.votacao.model.Votacao;
import br.com.votacao.repository.VotacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotacaoService {

    @Autowired
    private VotacaoRepository votacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Votacao salvar(Votacao votacao) {
        return votacaoRepository.save(votacao);
    }

    public Optional<Votacao> buscarPorId(Long id) {
        return votacaoRepository.findById(id);
    }

    public List<Votacao> listarTodas() {
        return votacaoRepository.findAll();
    }

    // Conversões

    public VotacaoDTO toDTO(Votacao votacao) {
        return modelMapper.map(votacao, VotacaoDTO.class);
    }

    public Votacao toEntity(VotacaoDTO dto) {
        return modelMapper.map(dto, Votacao.class);
    }

    public List<VotacaoDTO> toDTOList(List<Votacao> lista) {
        return lista.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
