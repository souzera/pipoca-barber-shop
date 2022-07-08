package com.br.pipoca.entity;

import com.br.pipoca.util.Sexo;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String telefone;
    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;
    private Date dtNascimento;
    @OneToOne
    @JoinColumn
    private Usuario usuario;

    public Cliente() {}

    public Cliente(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(long id, String nome, String telefone, Sexo sexo, Date dtNascimento) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
    }

    public Cliente(long id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public Cliente(long id, String nome, String telefone, Sexo sexo, Date dtNascimento, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", sexo=" + sexo +
                ", dtNascimento=" + dtNascimento +
                ", usuario=" + usuario +
                '}';
    }
}
