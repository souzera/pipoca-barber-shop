package com.br.pipoca.controller;

import com.br.pipoca.entity.Servico;
import com.br.pipoca.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/servico")
public class ServicoController {

    @Autowired
    ServicoRepository repository;

    @GetMapping
    public List<Servico> list(){
        return repository.findAll();
    }

    @PostMapping
    public void salvar (@RequestBody Servico servico){
        System.out.println("salvando servico " + servico.getDescricao());
        repository.save(servico);
    }

}
