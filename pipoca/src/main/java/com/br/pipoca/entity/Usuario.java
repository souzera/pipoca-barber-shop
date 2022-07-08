package com.br.pipoca.entity;

import com.br.pipoca.util.TipoUsuario;

import javax.persistence.*;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
    @Column(unique = true)
    private String login;
    private String senha;
    @Enumerated(EnumType.ORDINAL)
    private TipoUsuario tipoUsuario;

    public Usuario() {}

    public Usuario(long id) {
        this.id = id;
    }

    public Usuario(long id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(long id, String login, String senha, TipoUsuario tipoUsuario) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
