package br.com.votacao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class SessaoVotacao {
    @Id @GeneratedValue
    private Long id;
    private String titulo;
    private Date dataInicio;
    private Date dataFim;

    @Enumerated(EnumType.STRING)
    private StatusSessao status;

    @ManyToOne
    private Condominio condominio;

    @OneToMany(mappedBy = "sessao")
    private List<Votacao> votacoes;

    @OneToMany(mappedBy = "sessao")
    private List<Participante> participantes;
}