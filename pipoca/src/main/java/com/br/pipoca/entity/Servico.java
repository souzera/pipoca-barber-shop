package com.br.pipoca.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String descricao;
    private float valor;

    public Servico() {}

    public Servico(long id) {
        this.id = id;
    }

    public Servico(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Servico(long id, float valor) {
        this.id = id;
        this.valor = valor;
    }

    public Servico(long id, String descricao, float valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public long getId() {
        return id;
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

    @Override
    public String toString() {
        return descricao + " custa " + valor;
    }
}
