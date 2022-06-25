package com.br.pipoca.entity;

import javax.persistence.*;

@Entity
public class Venda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private Produto produto;

    @ManyToOne
    @JoinColumn
    private Atendimento atendimento;

    public Venda() {}

    public Venda(long id, Produto produto) {
        this.id = id;
        this.produto = produto;
    }

    public Venda(long id, Atendimento atendimento) {
        this.id = id;
        this.atendimento = atendimento;
    }

    public Venda(long id, Produto produto, Atendimento atendimento) {
        this.id = id;
        this.produto = produto;
        this.atendimento = atendimento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Atendimento getAgenda() {
        return atendimento;
    }

    public void setAgenda(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    @Override
    public String toString() {
        if (produto != null) {
            return "Venda: " + produto.getDescricao() + "\n\t Valor:" + produto.getValor();
        } else return "Venda: " + atendimento.getServico().getDescricao() +
                "\n\t Valor:" + atendimento.getServico().getValor();
    }
}
