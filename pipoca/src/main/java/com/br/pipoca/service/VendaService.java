package com.br.pipoca.service;

import com.br.pipoca.entity.Venda;
import com.br.pipoca.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public float receitaMensal(int mes, int ano){
        return 0;
    }
}
