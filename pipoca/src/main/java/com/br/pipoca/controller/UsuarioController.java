package com.br.pipoca.controller;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping(value = "/usuarios")
    public List<Usuario> list() throws IOException {
        return repository.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public Usuario buscarUsuario(@PathVariable(value = "id") long id){
        return repository.findById(id);
    }

    @PostMapping(value = "/user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUsuario(@RequestBody Usuario usuario){
        System.out.println("salvando user " + usuario);
        repository.save(usuario);
    }

    @DeleteMapping(value = "/user/del")
    public void deletarUsuario(@RequestBody Usuario usuario){
        repository.delete(usuario);
    }

    @DeleteMapping(value = "/user/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/user/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Usuario atualizarUsuario(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }
}

