package com.br.pipoca.service;

import com.br.pipoca.entity.Venda;
import com.br.pipoca.repository.VendaRepository;
import com.br.pipoca.util.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public List<Venda> vendasDiarias(Date date){
        Iterable<Venda> vendaIterable = this.vendaRepository.findAll();
        List<Venda> lista = new ArrayList<>();
        for (Venda v:vendaIterable) {
            if (v.getDate().getDate()==date.getDate() &&
                v.getDate().getMonth()==date.getMonth() &&
                v.getDate().getYear()==date.getYear()){
                lista.add(v);
            }
        }
        return lista;
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

    public List<Venda> vendasPorTipoPagamento(Pagamento pagamento){
        Iterable<Venda> vendaIterable = this.vendaRepository.findAll();
        List<Venda> lista = new ArrayList<>();
        for (Venda v:vendaIterable) {
            if (v.getPagamento()==pagamento){
                lista.add(v);
            }
        }
        return lista;
    }

    public List<Venda> vendasDinheiro(){
        return vendasPorTipoPagamento(Pagamento.DINHEIRO);
    }

    public List<Venda> vendasPix(){
        return vendasPorTipoPagamento(Pagamento.PIX);
    }

    public List<Venda> vendasCartao(){
        return vendasPorTipoPagamento(Pagamento.CARTAO);
    }

    public int[] pagamentoTypeCount(){
        return null;
    }

    public float receitaDiaria(Date date){
        List<Venda> vendas = vendasDiarias(date);
        float receita = 0;
        for (Venda v: vendas) {
            receita+= v.getValor();
        }
        return receita;
    }

    public int receitaSemanal(Date date) {
        return date.getTimezoneOffset();
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
