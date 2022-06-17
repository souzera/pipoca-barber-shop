package com.br.pipoca.dto;

import com.br.pipoca.entity.Cliente;
import com.br.pipoca.entity.Usuario;
import com.br.pipoca.model.Sexo;
import com.br.pipoca.model.TipoUsuario;

import java.util.Date;

public class ClienteDTO {
    private String login;
    private String senha;
    private String confirmaSenha;
    private String nome;
    private Date dtNascimento;
    private Sexo sexo;
    private String telefone;
    public ClienteDTO() {}

    public ClienteDTO(String nome, String telefone, Sexo sexo, Date dtNascimento,
                      String login, String senha, String confirmaSenha) {
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
        this.login = login;
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cliente toCliente(){
        Usuario usuario = new Usuario();
        usuario.setLogin(this.login);
        usuario.setSenha(this.senha);
        usuario.setTipoUsuario(TipoUsuario.CLIENTE);

        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
        cliente.setDtNascimento(this.dtNascimento);
        cliente.setSexo(this.sexo);
        cliente.setUsuario(usuario);
        return cliente;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", sexo=" + sexo +
                ", dtNascimento=" + dtNascimento +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
