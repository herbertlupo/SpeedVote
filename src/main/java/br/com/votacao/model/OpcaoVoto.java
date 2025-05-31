package br.com.votacao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OpcaoVoto {
    @Id @GeneratedValue
    private Long id;
    private String descricao;

    @ManyToOne
    private Votacao votacao;
}