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
    ClienteRepository clienteRepository;

    @GetMapping(value = "/clientes")
    public List<Cliente> list() throws IOException{
        return clienteRepository.findAll();
    }

    @GetMapping(value = "/cliente/{id}")
    public Cliente buscarCliente(@PathVariable(value="id") long id){
        return clienteRepository.findById(id);
    }

    @PostMapping(value = "/cliente/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        System.out.println("salvando cliente " + cliente.getNome());
        return clienteRepository.save(cliente);
    }
    @DeleteMapping(value = "/clientes/del")
    public void deletar(@RequestBody Cliente cliente){
        clienteRepository.delete(cliente);
    }

    @DeleteMapping(value = "/cliente/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        clienteRepository.deleteById(id);
    }

    @PutMapping(value = "/cliente/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cliente atualizar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }
}
