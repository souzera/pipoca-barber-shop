package com.br.pipoca.dto;

import com.br.pipoca.entity.Cliente;

import com.br.pipoca.util.Sexo;

public class ClienteDTO {
    private String nome;
    private Sexo sexo;
    private String telefone;


    public ClienteDTO() {}

    public ClienteDTO(String nome, String telefone, Sexo sexo) {
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
    }

    public Cliente toCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
        cliente.setSexo(this.sexo);
        return cliente;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", sexo=" + sexo +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
