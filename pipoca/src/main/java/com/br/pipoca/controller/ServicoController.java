package com.br.pipoca.controller;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Servico;
import com.br.pipoca.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ServicoController {

    @Autowired
    ServicoRepository repository;

    @GetMapping(value = "/servicos")
    public List<Servico> list() throws IOException {
        return repository.findAll();
    }

    @GetMapping(value = "/servico/{id}")
    public Servico buscarServicos(@RequestParam(value = "id") long id){
        return repository.findById(id);
    }

    @PostMapping(value = "/servico/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Servico servico){
        System.out.println("salvando serviço " + servico.getDescricao());
        repository.save(servico);
    }

    @DeleteMapping(value = "/servico/del")
    public void deletar(@RequestBody Servico servico){
        repository.delete(servico);
    }

    @DeleteMapping(value = "/servico/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/servico/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Servico atualizar(@RequestBody Servico servico){
        return repository.save(servico);
    }

}
