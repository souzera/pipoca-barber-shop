package com.br.pipoca.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;
    private String categoria;
    private String marca;
    private float valor;

    public Produto() {}

    public Produto(long id) {
        this.id = id;
    }

    public Produto(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Produto(long id, String descricao, float valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Produto(long id, String descricao,
                   String categoria, float valor) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
    }

    public Produto(long id, String descricao, String categoria, String marca, float valor) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.marca = marca;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return descricao.toUpperCase() + '\'' +
                ", categoria:'" + categoria + '\'' +
                ", marca:'" + marca + '\'' +
                ", valor:" + valor;
    }
}
