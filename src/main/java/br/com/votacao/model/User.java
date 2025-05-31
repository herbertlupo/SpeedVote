package br.com.votacao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String username;
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @ManyToOne
    private Condominio condominio;
}
