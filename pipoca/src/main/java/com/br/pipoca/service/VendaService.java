package com.br.pipoca.service;

import com.br.pipoca.entity.Venda;
import com.br.pipoca.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;

    public List<Venda> vendas(){
        return vendaRepository.findAll();
    }

    public List<Venda> vendaServicos(){
        return vendaRepository.vendaAgenda();
    }

    public List<Venda> vendaProduto(){
        return vendaRepository.vendaProdutos();
    }

    public Venda findById(long id){
        return vendaRepository.findById(id);
    }

    public List<Venda> vendasMensais(int mes, int ano){
        Iterable<Venda> vendaIterable = this.vendaRepository.findAll();
        List<Venda> lista = new ArrayList<>();
        for (Venda v:vendaIterable) {
            if (v.getDate().getMonth() == mes &&
                    v.getDate().getYear() == ano){
                lista.add(v);
            }
        }

        return lista;
    }

    public List<Venda> vendasAnuais(int ano){
        Iterable<Venda> vendaIterable = this.vendaRepository.findAll();
        List<Venda> lista = new ArrayList<>();
        for (Venda v:vendaIterable) {
            if (v.getDate().getYear() == ano){
                lista.add(v);
            }
        }

        return lista;
    }

    public float receitaMensal(int mes, int ano){
        List<Venda> vendas = vendasMensais(mes, ano);
        float receita = 0;
        for (Venda v: vendas) {
            receita+= v.getValor();
        }
        return receita;
    }

    public float receitaAnual(int ano){
        List<Venda> vendas = vendasAnuais(ano);
        float receita = 0;
        for (Venda v: vendas) {
            receita+= v.getValor();
        }
        return receita;
    }

    public List<Float> ganhosMensais(int ano){
        List<Float> ganhos = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            ganhos.add(receitaMensal(i, ano));
        }
        return ganhos;
    }

    public Venda save(Venda venda) {
        return vendaRepository.save(venda);
    }

    public void deleteById(long id) {
        vendaRepository.deleteById(id);
    }

    public void delete(Venda venda) {
        vendaRepository.delete(venda);
    }

    public float calcularValor(Venda venda){
        //trocar Produto por itensProduto
        float valor = 0;
        if (venda.getProduto() != null && venda.getAtendimento() != null){
            valor = venda.getProduto().getValor() + venda.getAtendimento().getServico().getValor();
        } else if (!(venda.getProduto()!= null)){
            valor = venda.getAtendimento().getServico().getValor();
        } else if (!(venda.getAtendimento() != null)){
            valor = venda.getProduto().getValor();
        }

        return valor;
    }
}
