package com.br.pipoca.controller;

import com.br.pipoca.entity.Horario;
import com.br.pipoca.entity.Produto;
import com.br.pipoca.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProdutoController {


    @Autowired
    ProdutoRepository repository;

    @GetMapping(value = "/produtos")
    public List<Produto> list() throws IOException {
        return repository.findAll();
    }

    @GetMapping(value = "/produto/{id}")
    public Produto buscarProduto(@RequestParam(value = "id") long id){
        return repository.findById(id);
    }

    @PostMapping(value = "/produto/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Produto produto){
        System.out.println("salvando serviço " + produto);
        repository.save(produto);
    }

    @DeleteMapping(value = "/produto/del")
    public void deletar(@RequestBody Produto produto){
        repository.delete(produto);
    }

    @DeleteMapping(value = "/produto/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/produto/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Produto atualizar(@RequestBody Produto produto){
        return repository.save(produto);
    }
}
