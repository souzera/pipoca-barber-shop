package com.br.pipoca.dto;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.model.TipoUsuario;

public class UsuarioDTO {

    private String login;
    private String senha;

    public UsuarioDTO() {}

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

    public Usuario toUsuario(){
        Usuario usuario = new Usuario();
        usuario.setLogin(this.login);
        usuario.setSenha(this.senha);
        return usuario;
    }

    public Usuario toUsuario(String login, String senha){
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(senha);
        return usuario;
    }

    public TipoUsuario clienteType(){
        return TipoUsuario.CLIENTE;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
