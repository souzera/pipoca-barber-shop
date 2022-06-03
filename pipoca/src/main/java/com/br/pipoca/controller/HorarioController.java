package com.br.pipoca.controller;

import com.br.pipoca.entity.Horario;
import com.br.pipoca.entity.Produto;
import com.br.pipoca.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/horario")
public class HorarioController {

    @Autowired
    HorarioRepository repository;

    @GetMapping
    public List<Horario> list() throws IOException {
        return repository.findAll();
    }

    @PostMapping
    public void salvar (@RequestBody Horario horario){
        System.out.println("salvando horario " + horario.getData());
        repository.save(horario);
    }

}
