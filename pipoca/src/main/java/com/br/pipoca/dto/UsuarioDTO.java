package com.br.pipoca.dto;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.util.TipoUsuario;

public class UsuarioDTO {

    private String login;
    private String senha;
    private String confirmaSenha;

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

    public String getConfirmaSenha() {return confirmaSenha;}

    public void setConfirmaSenha(String confirmaSenha) {this.confirmaSenha = confirmaSenha;}

    public Usuario toUsuario(){
        Usuario usuario = new Usuario();
        usuario.setLogin(this.login);
        usuario.setSenha(this.senha);
        return usuario;
    }

    public Usuario toUsuarioCliente(){
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setTipoUsuario(clienteType());
        return usuario;
    }

    public Usuario toUsuarioFuncionario(){
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setTipoUsuario(funcionarioType());
        return usuario;
    }

    public TipoUsuario clienteType(){return TipoUsuario.CLIENTE;}
    public TipoUsuario funcionarioType(){return TipoUsuario.BARBEIRO;}

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
