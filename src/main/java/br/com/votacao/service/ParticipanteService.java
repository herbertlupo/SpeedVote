package br.com.votacao.service;

import br.com.votacao.dto.CadastroParticipanteRequestDTO;
import br.com.votacao.model.Participante;
import br.com.votacao.repository.ParticipanteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Participante salvar(Participante participante) {
        return participanteRepository.save(participante);
    }

    public void remover(Long id) {
        participanteRepository.deleteById(id);
    }

    public Optional<Participante> buscarPorId(Long id) {
        return participanteRepository.findById(id);
    }

    public Optional<Participante> buscarPorToken(String token) {
        return participanteRepository.findByTokenVotacao(token);
    }

    public List<Participante> listarTodos() {
        return participanteRepository.findAll();
    }

    // Conversões

    public CadastroParticipanteRequestDTO toDTO(Participante participante) {
        return modelMapper.map(participante, CadastroParticipanteRequestDTO.class);
    }

    public Participante toEntity(CadastroParticipanteRequestDTO dto) {
        return modelMapper.map(dto, Participante.class);
    }

    public List<CadastroParticipanteRequestDTO> toDTOList(List<Participante> lista) {
        return lista.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
