package com.br.pipoca.controller;

import com.br.pipoca.entity.Agenda;
import com.br.pipoca.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/agenda")
public class AgendaController {

    @Autowired
    AgendaRepository repository;

    @GetMapping
    public List<Agenda> list() throws IOException {
        return repository.findAll();
    }

    @PostMapping
    public void salvar (@RequestBody Agenda agenda){
        System.out.println("salvando agendamento " + agenda.getHorario());
        repository.save(agenda);
    }
}
