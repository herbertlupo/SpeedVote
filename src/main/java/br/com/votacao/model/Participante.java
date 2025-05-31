package br.com.votacao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Participante {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String telefone;
    private String tokenVotacao;
    private boolean representaJuridico;
    private Double pesoComputado;

    @ManyToMany
    private List<Unidade> unidades;

    @ManyToOne
    private SessaoVotacao sessao; // vincula à sessão da assembleia
}
