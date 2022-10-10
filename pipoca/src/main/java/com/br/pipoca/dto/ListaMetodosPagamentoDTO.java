package com.br.pipoca.dto;

import com.br.pipoca.entity.Venda;
import com.br.pipoca.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ListaMetodosPagamentoDTO {


    private String metodo;
    private List<Venda> lista;
    private float total;

    public ListaMetodosPagamentoDTO() {}

    public ListaMetodosPagamentoDTO(String metodo, List<Venda> lista) {
        this.metodo = metodo;
        this.lista = lista;
        this.total = somatorioVendas(lista);
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public List<Venda> getLista() {
        return lista;
    }

    public void setLista(List<Venda> lista) {
        this.lista = lista;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float somatorioVendas(List<Venda> vendas){
        if (!(vendas != null)){return 0;}
        float total = 0;
        for (Venda v: vendas){
            total += v.getValor();
        }
        return total;
    }
}
