package com.br.pipoca.controller;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository repository;

    @GetMapping(value = "/funcionarios")
    public List<Funcionario> list() throws IOException{
        return repository.findAll();
    }

    @GetMapping(value = "/funcionario/{id}")
    public Funcionario buscarFuncionario(@PathVariable(value = "id") long id){
        return repository.findById(id);
    }

    @PostMapping(value = "/funcionario/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar (@RequestBody Funcionario funcionario){
        System.out.println("salvando funcionario " + funcionario.getNome());
        repository.save(funcionario);
    }

    @DeleteMapping(value = "/funcionario/del")
    public void deletar(@RequestBody Funcionario funcionario){
        repository.delete(funcionario);
    }

    @DeleteMapping(value = "/funcionario/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/funcionario/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Funcionario atualizar(@RequestBody Funcionario funcionario){
        return repository.save(funcionario);
    }

}
