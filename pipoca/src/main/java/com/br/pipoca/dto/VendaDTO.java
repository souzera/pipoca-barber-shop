package com.br.pipoca.dto;

import com.br.pipoca.entity.Venda;
import org.springframework.ui.Model;

public class VendaDTO {

    private Model model;
    private Venda venda;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
