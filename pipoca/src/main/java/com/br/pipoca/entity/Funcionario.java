package com.br.pipoca.entity;


import com.br.pipoca.util.Cargo;
import com.br.pipoca.util.Sexo;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

    private String apelido;
    private String telefone;

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

    public Funcionario(long id, String nome, String telefone,
                       Date dtNascimento, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
        this.cargo = cargo;
    }

    public Funcionario(long id, String nome, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }

    public Funcionario(long id, String nome, String telefone, Sexo sexo,
                       Date dtNascimento, Cargo cargo, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
        this.cargo = cargo;
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getApelido() {
        if (!(apelido != null)){return getFirstName();}
        return apelido;
    }
    public void setApelido(String apelido) {this.apelido = apelido;}

    public String getFirstName(){
        return nome.substring(0, nome.indexOf(" "));
    }


}
