package com.br.pipoca.entity;


import com.br.pipoca.util.Cargo;
import com.br.pipoca.util.Sexo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter @Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String telefone;

    @Enumerated
    private Sexo sexo;
    private Date dtNascimento;
    @Enumerated
    private Cargo cargo;
    @OneToOne
    @JoinColumn
    private Usuario usuario;

    public Funcionario(){}

    public Funcionario(Long id) {
        this.id = id;
    }

    public Funcionario(long id, String nome, String telefone, Sexo sexo,
                       Date dtNascimento, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
        this.cargo = cargo;
    }

    public Funcionario(long id, String nome, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }

    public Funcionario(long id, String nome, String telefone, Sexo sexo, Date dtNascimento, Cargo cargo, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
        this.cargo = cargo;
        this.usuario = usuario;
    }

}
