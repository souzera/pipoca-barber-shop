package com.br.pipoca.dto;

import com.br.pipoca.entity.Atendimento;
import com.br.pipoca.entity.Cliente;
import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Servico;
import com.br.pipoca.util.Hora;

import java.sql.Date;

public class AtendimentoDTO {
    private Cliente cliente;
    private Date dia;
    private Servico servico;

    public AtendimentoDTO() {}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Atendimento toAtendimento(){
        Atendimento atendimento = new Atendimento();
        atendimento.setDia(this.dia);
        atendimento.setCliente(this.cliente);
        atendimento.setServico(this.servico);
        return atendimento;
    }
}
