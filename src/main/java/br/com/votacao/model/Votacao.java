package br.com.votacao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Votacao {
    @Id @GeneratedValue
    private Long id;
    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoVotacao tipo;
    private int maximoEscolhas;

    @ManyToOne
    private SessaoVotacao sessao;

    @OneToMany(mappedBy = "votacao")
    private List<OpcaoVoto> opcoes;
}