package com.br.pipoca.entity;

import com.br.pipoca.util.DateConverter;
import com.br.pipoca.util.Hora;
import com.br.pipoca.util.StatusHorario;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateTextFormat(){
        SimpleDateFormat patternDate = new SimpleDateFormat("dd/MM/yyyy");
        return patternDate.format(DateConverter.toJavaDate(date));
    }

    @Override
    public String toString() {
        return "Horario{" +
                "id=" + id +
                ", funcionario=" + funcionario +
                ", hora=" + hora +
                ", statusHorario=" + statusHorario +
                ", date=" + date +
                '}';
    }
}
