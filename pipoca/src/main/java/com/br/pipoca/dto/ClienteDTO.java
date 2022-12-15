package com.br.pipoca.dto;

import com.br.pipoca.entity.Cliente;
import com.br.pipoca.util.DateConverter;
import com.br.pipoca.util.Sexo;

import java.sql.Date;
public class ClienteDTO {
    private String nome;
    private Date dtNascimento;
    private Sexo sexo;
    private String telefone;


    public ClienteDTO() {}

    public ClienteDTO(String nome, String telefone, Sexo sexo, Date dtNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
    }

    public Cliente toCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
        if (this.dtNascimento != null){
            cliente.setDtNascimento(this.dtNascimento);
            System.out.println(this.dtNascimento);
        }
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
        if (dtNascimento != null){
            this.dtNascimento = dtNascimento;
        }
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
}
