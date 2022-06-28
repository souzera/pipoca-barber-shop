package com.br.pipoca.controller;

import com.br.pipoca.entity.Atendimento;
import com.br.pipoca.service.AtendimentoService;
import com.br.pipoca.util.Hora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AtendimentoController {

    @Autowired
    AtendimentoService atendimentoService;

    @GetMapping(value = "/agendamentos")
    public List<Atendimento> list() throws IOException {
        return atendimentoService.agendamentos();
    }

    @GetMapping(value = "/agenda/{id}")
    public Atendimento buscarHorario(@PathVariable(value = "id") long id){
        return atendimentoService.buscarPorId(id);
    }

    @PostMapping(value = "/agenda/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Atendimento atendimento){

    }

    @DeleteMapping(value = "/agenda/del")
    public void deletar(@RequestBody Atendimento atendimento){

    }

    @DeleteMapping(value = "/agenda/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        atendimentoService.deleteById(id);
    }

    @PutMapping(value = "/agenda/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Atendimento atualizar(@RequestBody Atendimento atendimento){
        return atendimentoService.agendar(atendimento);
    }

    @GetMapping(value = "/vagas")
    public List<Hora> getVagas(String dia) throws IOException {
        int ano = Integer.parseInt(dia.substring(0,4));
        int mes = Integer.parseInt(dia.substring(5,7));
        int data = Integer.parseInt(dia.substring(8,10));
        Date date = new Date(ano, mes, data);
        System.out.println(atendimentoService.vagas(date));
        return atendimentoService.vagas(date);
    }
}
