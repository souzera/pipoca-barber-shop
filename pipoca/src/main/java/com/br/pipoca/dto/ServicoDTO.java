package com.br.pipoca.dto;

import com.br.pipoca.entity.Servico;

public class ServicoDTO {

    private String descricao;
    private float valor;

    public ServicoDTO() {}

    public ServicoDTO(String descricao, float valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Servico toServico(){
        Servico servico = new Servico();
        servico.setDescricao(this.descricao);
        servico.setValor(this.valor);
        return servico;
    }
}
