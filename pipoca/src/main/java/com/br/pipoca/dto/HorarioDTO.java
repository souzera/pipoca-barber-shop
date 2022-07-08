package com.br.pipoca.dto;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Horario;
import com.br.pipoca.service.FuncionarioService;
import com.br.pipoca.util.Hora;
import com.br.pipoca.util.StatusHorario;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class HorarioDTO {

    private long funcionarioId;
    private Hora hora;
    private Date date;

    public HorarioDTO() {}
    public Horario toHorario(){
        Horario horario = new Horario();
        horario.setHora(this.hora);
        horario.setStatusHorario(StatusHorario.DISPONIVEL);
        horario.setDate(this.date);
        //mudar na hora do cadastr
        //horario.setFuncionario(funcionarioService.findById(funcionarioId));
        return horario;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }
}
