package com.br.pipoca.dto;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Horario;
import com.br.pipoca.util.Hora;
import com.br.pipoca.util.StatusHorario;

public class HorarioDTO {

    private Funcionario funcionario;
    private Hora hora;

    public HorarioDTO() {}

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

    public Horario toHorario(){
        Horario horario = new Horario();
        horario.setFuncionario(this.funcionario);
        horario.setHora(this.hora);
        horario.setStatusHorario(StatusHorario.DISPONIVEL);
        return horario;
    }
}
