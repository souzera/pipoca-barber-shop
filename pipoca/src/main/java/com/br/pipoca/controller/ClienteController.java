package com.br.pipoca.controller;

import com.br.pipoca.entity.Cliente;
import com.br.pipoca.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping(value = "/clientes")
    public List<Cliente> list() throws IOException{
        return clienteService.clientes();
    }

    @GetMapping(value = "/cliente/{id}")
    public Cliente buscarCliente(@PathVariable(value="id") long id){
        return clienteService.buscarPorID(id);
    }

    @GetMapping(value = "/cliente/pesquisa-nome")
    public List<Cliente> consultaName(@RequestBody String nome){
        return clienteService.pesquisaPorNome(nome);
    }

    @PostMapping(value = "/cliente/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        System.out.println("salvando cliente " + cliente.getNome());
        return clienteService.saveCliente(cliente);
    }
    @DeleteMapping(value = "/clientes/del")
    public void deletar(@RequestBody Cliente cliente){
        clienteService.deleteById(cliente.getId());
    }

    @DeleteMapping(value = "/cliente/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        clienteService.deleteById(id);
    }

    @PutMapping(value = "/cliente/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cliente atualizar(@RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);
    }

}
