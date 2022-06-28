package com.br.pipoca.entity;

import com.br.pipoca.util.Pagamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    @Override
    public String toString() {
        if (produto != null) {
            return "Venda: " + produto.getDescricao() + "\n\t Valor:" + produto.getValor();
        } else return "Venda: " + atendimento.getServico().getDescricao() +
                "\n\t Valor:" + atendimento.getServico().getValor();
    }
}
