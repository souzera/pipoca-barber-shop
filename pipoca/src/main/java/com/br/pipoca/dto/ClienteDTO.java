package com.br.pipoca.dto;

import com.br.pipoca.entity.Cliente;
import com.br.pipoca.entity.Usuario;
import com.br.pipoca.model.Sexo;

import java.util.Date;

public class ClienteDTO {

    private String nome;
    private String telefone;
    private Sexo sexo;
    private Date dtNascimento;
    private String login;
    private String senha;

    private UsuarioDTO usuarioDTO;

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

    public Cliente toCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
        cliente.setDtNascimento(this.dtNascimento);
        cliente.setSexo(this.sexo);
        cliente.setUsuario(this.usuarioDTO.toUsuarioClienteType(this.login, this.senha));
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
                ", usuarioDTO=" + usuarioDTO +
                '}';
    }
}
