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
    private Agenda agenda;

    public Venda() {}

    public Venda(long id, Produto produto) {
        this.id = id;
        this.produto = produto;
    }

    public Venda(long id, Agenda agenda) {
        this.id = id;
        this.agenda = agenda;
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

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Override
    public String toString() {
        if (produto != null) {
            return "Venda: " + produto.getDescricao() + "\n\t Valor:" + produto.getValor();
        } else return "Venda: " + agenda.getServico().getDescricao() +
                "\n\t Valor:" + agenda.getServico().getValor();
    }
}
