package com.br.pipoca.entity;

import com.br.pipoca.util.Hora;
import com.br.pipoca.util.StatusHorario;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne
    @JoinColumn
    private Funcionario funcionario;
    @Enumerated
    private Hora hora;

    @Enumerated
    private StatusHorario statusHorario;

    public Horario() {}

    public Horario(long id, Funcionario funcionario, Hora hora, StatusHorario status) {
        this.id = id;
        this.funcionario = funcionario;
        this.hora = hora;
    }

    public Horario(long id, Funcionario funcionario) {
        this.id = id;
        this.funcionario = funcionario;
    }

    public Horario(long id, Hora hora) {
        this.id = id;
        this.hora = hora;
    }

    public Horario(long id, Funcionario funcionario, Hora hora) {
        this.id = id;
        this.funcionario = funcionario;
        this.hora = hora;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public StatusHorario getStatusHorario() {
        return statusHorario;
    }

    public void setStatusHorario(StatusHorario statusHorario) {
        this.statusHorario = statusHorario;
    }
}
