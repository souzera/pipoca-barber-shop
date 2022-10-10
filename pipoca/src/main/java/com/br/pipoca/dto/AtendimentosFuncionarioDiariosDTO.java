package com.br.pipoca.dto;

import com.br.pipoca.entity.Atendimento;
import com.br.pipoca.entity.Funcionario;

import java.sql.Date;
import java.util.List;

public class AtendimentosFuncionarioDiariosDTO {

    Funcionario funcionario;
    List<Atendimento> atendimentos;
    Date date;

    public AtendimentosFuncionarioDiariosDTO() {}

    public AtendimentosFuncionarioDiariosDTO(Funcionario funcionario, List<Atendimento> atendimentos) {
        this.funcionario = funcionario;
        this.atendimentos = atendimentos;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
