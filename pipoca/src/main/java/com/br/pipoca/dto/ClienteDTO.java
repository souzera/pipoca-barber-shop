package com.br.pipoca.dto;

import com.br.pipoca.entity.Cliente;
import com.br.pipoca.entity.Usuario;
import com.br.pipoca.model.Sexo;
import com.br.pipoca.model.TipoUsuario;
import com.br.pipoca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ClienteDTO {
    private String nome;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dtNascimento;
    private Sexo sexo;
    private String telefone;

    private UsuarioDTO usuarioDTO;

    public ClienteDTO() {}

    public ClienteDTO(String nome, String telefone, Sexo sexo, Date dtNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
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
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
        cliente.setDtNascimento(this.dtNascimento);
        cliente.setSexo(this.sexo);
        return cliente;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", sexo=" + sexo +
                ", dtNascimento=" + dtNascimento +
                '}';
    }
}
