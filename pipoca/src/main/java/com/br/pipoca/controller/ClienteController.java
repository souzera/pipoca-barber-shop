package com.br.pipoca.controller;

import com.br.pipoca.entity.Cliente;
import com.br.pipoca.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @GetMapping(value = "/clientes")
    public List<Cliente> list() throws IOException{
        return repository.findAll();
    }

    @GetMapping(value = "/cliente/{id}")
    public Cliente buscarCliente(@PathVariable(value="id") long id){
        return repository.findById(id);
    }

    @PostMapping(value = "/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        System.out.println("salvando cliente " + cliente.getNome());
        return repository.save(cliente);
    }
    @DeleteMapping(value = "/clientes")
    public void deletar(@RequestBody Cliente cliente){
        repository.delete(cliente);
    }

    @DeleteMapping(value = "/cliente/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        repository.deleteById(id);
    }
}
