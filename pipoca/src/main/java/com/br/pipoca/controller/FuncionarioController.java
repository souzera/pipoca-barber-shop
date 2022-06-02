package com.br.pipoca.controller;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository repository;

    @GetMapping
    public List<Funcionario> list() throws IOException{
        return repository.findAll();
    }

    @PostMapping
    public void salvar (@RequestBody Funcionario funcionario){
        System.out.println("salvando funcionario " + funcionario.getNome());
        repository.save(funcionario);
    }

}
