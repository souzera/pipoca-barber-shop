package com.br.pipoca.dto;

import com.br.pipoca.entity.Atendimento;

public class AtendimentoDTO {
    private long cliente_id;
    private long servico_id;
    public AtendimentoDTO() {}

    public long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public long getServico_id() {
        return servico_id;
    }

    public void setServico_id(long servico_id) {
        this.servico_id = servico_id;
    }

    public Atendimento toAtendimento(){
        Atendimento atendimento = new Atendimento();
        //mudar na hora do cadastro
        //atendimento.setCliente(clienteService.buscarPorID(this.cliente_id));
        //atendimento.setServico(servicoService.buscarPorID(this.servico_id));
        return atendimento;
    }


}
