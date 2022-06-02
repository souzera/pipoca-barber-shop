package com.br.pipoca.entity;


import com.br.pipoca.model.Sexo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String telefone;
    private Sexo sexo;
    private Date dtNascimento;
    private String funcao;

    public Funcionario(){}

    public Funcionario(Long id) {
        this.id = id;
    }

    public Funcionario(long id, String nome, String telefone, Sexo sexo,
                       Date dtNascimento, String funcao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
        this.funcao = funcao;
    }

    public Funcionario(long id, String nome, String funcao) {
        this.id = id;
        this.nome = nome;
        this.funcao = funcao;
    }

    public long getId() {
        return id;
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
