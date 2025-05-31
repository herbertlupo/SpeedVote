package br.com.votacao.service;

import br.com.votacao.dto.VotoRequestDTO;
import br.com.votacao.model.Voto;
import br.com.votacao.repository.VotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Voto salvar(Voto voto) {
        return votoRepository.save(voto);
    }

    public Optional<Voto> buscarPorId(Long id) {
        return votoRepository.findById(id);
    }

    public List<Voto> listarTodos() {
        return votoRepository.findAll();
    }

    // Conversões

    public VotoRequestDTO toDTO(Voto voto) {
        return modelMapper.map(voto, VotoRequestDTO.class);
    }

    public Voto toEntity(VotoRequestDTO dto) {
        return modelMapper.map(dto, Voto.class);
    }

    public List<VotoRequestDTO> toDTOList(List<Voto> votos) {
        return votos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
