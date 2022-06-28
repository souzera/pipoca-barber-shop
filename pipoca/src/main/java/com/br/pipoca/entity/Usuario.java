package com.br.pipoca.entity;

import com.br.pipoca.util.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
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

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha.hashCode() + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}
