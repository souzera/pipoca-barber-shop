package com.br.pipoca.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter @Setter
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn
    private Cliente cliente;
    @OneToOne
    @JoinColumn
    private Horario horario;
    @ManyToOne
    @JoinColumn
    private Servico servico;
    private Date dia;

    public Atendimento() {
    }

    public Atendimento(long id, Cliente cliente, Horario horario, Servico servico) {
        this.id = id;
        this.cliente = cliente;
        this.horario = horario;
        this.servico = servico;
    }

    public Atendimento(long id, Cliente cliente, Horario horario) {
        this.id = id;
        this.cliente = cliente;
        this.horario = horario;
    }
}
