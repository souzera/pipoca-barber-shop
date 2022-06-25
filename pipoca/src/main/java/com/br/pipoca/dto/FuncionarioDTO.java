package com.br.pipoca.dto;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.util.Cargo;
import com.br.pipoca.util.Sexo;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class FuncionarioDTO {

    private String nome;
    private Date dtNascimento;
    private Sexo sexo;
    private String telefone;
    private Cargo cargo;

    private UsuarioDTO usuarioDTO;

    public FuncionarioDTO() {}

    public FuncionarioDTO(String nome, Date dtNascimento, Sexo sexo, String telefone, Cargo cargo) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.sexo = sexo;
        this.telefone = telefone;
        this.cargo = cargo;
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public Funcionario toFuncionario(){
        Funcionario funcionario = new Funcionario();
        funcionario.setDtNascimento(this.dtNascimento);
        funcionario.setCargo(this.cargo);
        funcionario.setNome(this.nome);
        funcionario.setSexo(this.sexo);
        funcionario.setTelefone(this.telefone);
        return funcionario;
    }
}
