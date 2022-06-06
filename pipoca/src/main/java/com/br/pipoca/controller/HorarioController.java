package com.br.pipoca.controller;

import com.br.pipoca.entity.Horario;
import com.br.pipoca.entity.Servico;
import com.br.pipoca.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class HorarioController {

    @Autowired
    HorarioRepository repository;


    @GetMapping(value = "/horarios")
    public List<Horario> list() throws IOException {
        return repository.findAll();
    }

    @GetMapping(value = "/horario/{id}")
    public Horario buscarHorario(@RequestParam(value = "id") long id){
        return repository.findById(id);
    }

    @PostMapping(value = "/horario/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Horario horario){
        System.out.println("salvando serviço " + horario.getData());
        repository.save(horario);
    }

    @DeleteMapping(value = "/horario/del")
    public void deletar(@RequestBody Horario horario){
        repository.delete(horario);
    }

    @DeleteMapping(value = "/horario/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/horario/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Horario atualizar(@RequestBody Horario horario){
        return repository.save(horario);
    }

}
