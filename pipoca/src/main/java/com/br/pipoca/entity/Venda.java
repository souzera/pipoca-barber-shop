package com.br.pipoca.entity;

import com.br.pipoca.util.Pagamento;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private Produto produto;

    @OneToOne
    @JoinColumn
    private Atendimento atendimento;

    @Enumerated
    private Pagamento pagamento;

    private Date date;

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

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
