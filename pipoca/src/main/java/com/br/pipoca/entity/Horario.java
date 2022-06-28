package com.br.pipoca.entity;

import com.br.pipoca.util.Hora;
import com.br.pipoca.util.StatusHorario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
}
