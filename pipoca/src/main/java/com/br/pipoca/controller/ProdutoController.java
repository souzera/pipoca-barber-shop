package com.br.pipoca.controller;

import com.br.pipoca.entity.Produto;
import com.br.pipoca.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {


    @Autowired
    ProdutoRepository repository;

    @GetMapping
    public List<Produto> list() throws IOException {
        return repository.findAll();
    }

    @PostMapping
    public void salvar (@RequestBody Produto produto){
        System.out.println("salvando produto " + produto.getDescricao());
        repository.save(produto);
    }
}
