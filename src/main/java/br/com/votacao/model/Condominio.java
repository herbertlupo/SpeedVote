package br.com.votacao.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Condominio {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;

    @OneToMany(mappedBy = "condominio")
    private List<Unidade> unidades;
}

