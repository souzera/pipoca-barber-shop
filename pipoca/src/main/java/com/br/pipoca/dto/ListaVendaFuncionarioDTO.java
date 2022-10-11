package com.br.pipoca.dto;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Venda;

import java.util.List;

public class ListaVendaFuncionarioDTO {

    Funcionario funcionario;
    List<Venda> vendas;
    float receita;

    public ListaVendaFuncionarioDTO() {}

    public ListaVendaFuncionarioDTO(Funcionario funcionario, List<Venda> vendas) {
        this.funcionario = funcionario;
        this.vendas = vendas;
        this.receita = somatorioVendas(vendas);
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public float getReceita() {
        return receita;
    }

    public void setReceita(float receita) {
        this.receita = receita;
    }

    @Override
    public String toString() {
        return "ListaVendaFuncionarioDTO{" +
                "funcionario=" + funcionario.getNome() +
                ", vendas=" + vendas +
                ", receita=" + receita +
                '}';
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
