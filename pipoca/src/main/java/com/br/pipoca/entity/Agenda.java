package com.br.pipoca.entity;

import javax.persistence.*;

@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn
    private Cliente cliente;

    @OneToOne
    @JoinColumn
    private Horario horario;

    @OneToOne
    @JoinColumn
    private Servico servico;

    public Agenda() {
    }

    public Agenda(long id, Cliente cliente, Horario horario, Servico servico) {
        this.id = id;
        this.cliente = cliente;
        this.horario = horario;
        this.servico = servico;
    }

    public Agenda(long id, Cliente cliente, Horario horario) {
        this.id = id;
        this.cliente = cliente;
        this.horario = horario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
