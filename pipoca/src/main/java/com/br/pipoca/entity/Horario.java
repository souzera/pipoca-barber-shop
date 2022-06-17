package com.br.pipoca.entity;

import com.br.pipoca.model.StatusHorario;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne
    @JoinColumn
    private Funcionario funcionario;
    private Date data;
    private StatusHorario status;

    public Horario(){};

    public Horario(long id) {
        this.id = id;
    }

    public Horario(long id, Funcionario funcionario) {
        this.id = id;
        this.funcionario = funcionario;
        this.status = StatusHorario.DISPONIVEL;
    }

    public Horario(long id, Date data) {
        this.id = id;
        this.data = data;
        this.status = StatusHorario.DISPONIVEL;
    }

    public Horario(long id, Funcionario funcionario, Date data) {
        this.id = id;
        this.funcionario = funcionario;
        this.data = data;
        this.status = StatusHorario.DISPONIVEL;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return funcionario + "tem um horário marcado no dia " + getData();
    }

    public StatusHorario getStatus() {
        return status;
    }

    public void setStatus(StatusHorario status) {
        this.status = status;
    }
}
