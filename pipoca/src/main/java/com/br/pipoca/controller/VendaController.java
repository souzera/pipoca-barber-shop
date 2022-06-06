package com.br.pipoca.controller;

import com.br.pipoca.entity.Horario;
import com.br.pipoca.entity.Venda;
import com.br.pipoca.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class VendaController {

    @Autowired
    VendaRepository repository;

    @GetMapping(value = "/vendas")
    public List<Venda> list() throws IOException {
        return repository.findAll();
    }

    @GetMapping(value = "/venda/{id}")
    public Venda buscarVenda(@RequestParam(value = "id") long id){
        return repository.findById(id);
    }

    @PostMapping(value = "/venda/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Venda venda){
        System.out.println("salvando serviço " + venda);
        repository.save(venda);
    }

    @DeleteMapping(value = "/venda/del")
    public void deletarVenda(@RequestBody Venda venda){
        repository.delete(venda);
    }

    @DeleteMapping(value = "/venda/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/venda/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Venda atualizar(@RequestBody Venda venda){
        return repository.save(venda);
    }
}
