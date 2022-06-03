package com.br.pipoca.controller;

import com.br.pipoca.entity.Venda;
import com.br.pipoca.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/venda")
public class VendaController {

    @Autowired
    VendaRepository repository;

    @GetMapping
    public List<Venda> list() throws IOException {
        return repository.findAll();
    }

    @PostMapping
    public void salvar (@RequestBody Venda venda){
        System.out.println("salvando venda " + venda);
        repository.save(venda);
    }
}
