package br.com.votacao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Voto {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Participante participante;

    @ManyToOne
    private Votacao votacao;

    private String opcoesSelecionadas; // IDs separados por vírgulas
    private Double pesoComputado;
}