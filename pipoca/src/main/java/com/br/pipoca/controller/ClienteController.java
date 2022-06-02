package com.br.pipoca.controller;

import com.br.pipoca.entity.Cliente;
import com.br.pipoca.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @GetMapping
    public List<Cliente> list() throws IOException{
        return repository.findAll();
    }

    @PostMapping
    public void salvar (@RequestBody Cliente cliente){
        System.out.println("salvando cliente " + cliente.getNome());
        repository.save(cliente);
    }
}
