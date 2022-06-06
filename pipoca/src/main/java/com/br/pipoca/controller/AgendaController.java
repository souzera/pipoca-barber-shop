package com.br.pipoca.controller;

import com.br.pipoca.entity.Agenda;
import com.br.pipoca.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AgendaController {

    @Autowired
    AgendaRepository repository;

    @GetMapping(value = "/agendamentos")
    public List<Agenda> list() throws IOException {
        return repository.findAll();
    }

    @GetMapping(value = "/agenda/{id}")
    public Agenda buscarHorario(@PathVariable(value = "id") long id){
        return repository.findById(id);
    }

    @PostMapping(value = "/agenda/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Agenda agenda){
        System.out.println("salvando agenda " + agenda);
        repository.save(agenda);
    }

    @DeleteMapping(value = "/agenda/del")
    public void deletar(@RequestBody Agenda agenda){
        repository.delete(agenda);
    }

    @DeleteMapping(value = "/agenda/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/agenda/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Agenda atualizar(@RequestBody Agenda agenda){
        return repository.save(agenda);
    }
}
