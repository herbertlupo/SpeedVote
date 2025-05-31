package br.com.votacao.model;

import jakarta.persistence.*;

@Entity
public class Unidade {
    @Id
    @GeneratedValue
    private Long id;
    private String numero;
    private String bloco;
    private Double fracaoIdeal;

    @ManyToOne
    private Condominio condominio;
}
