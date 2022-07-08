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
        atendimentoService.agendar(atendimento);
    }

    @DeleteMapping(value = "/agenda/del")
    public void deletar(@RequestBody Atendimento atendimento){
        atendimentoService.deleteById(atendimento.getId());
    }

    //@DeleteMapping(value = "/agenda/del/{id}")
    //public void deletarPorID(@PathVariable(value = "id") long id){
        //atendimentoService.deleteById(id);
    //}

    @DeleteMapping(value = "/agenda/delete{id}")
    public void deletarPorID(@RequestParam(value = "id") Long id){
        atendimentoService.deleteById(id);
    }

    @PutMapping(value = "/agenda/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Atendimento atualizar(@RequestBody Atendimento atendimento){
        return atendimentoService.agendar(atendimento);
    }

    @GetMapping(value = "/vagas")
    public List<Hora> getVagas(@RequestBody String dia) throws IOException {
        System.out.println("ola mundo");
        return null;
    }
}
