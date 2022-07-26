package com.br.pipoca.controller;

import com.br.pipoca.entity.Venda;
import com.br.pipoca.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class VendaController {

    @Autowired
    VendaService vendaService;

    @GetMapping(value = "/vendas")
    public List<Venda> list() throws IOException {
        return vendaService.vendas();
    }

    @GetMapping(value = "/venda/{id}")
    public Venda buscarVenda(@PathVariable(value = "id") long id){
        return vendaService.findById(id);
    }

    @PostMapping(value = "/venda/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void venda(@RequestBody Venda venda){
        System.out.println("salvando venda " + venda);
        //setando valor da venda
        venda.setValor(vendaService.calcularValor(venda));
        //===============================================
        vendaService.save(venda);
    }

    @DeleteMapping(value = "/venda/del")
    public void deletarVenda(@RequestBody Venda venda){
        vendaService.delete(venda);
    }

    @DeleteMapping(value = "/venda/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        vendaService.deleteById(id);
    }

    @PutMapping(value = "/venda/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Venda atualizar(@RequestBody Venda venda){
        return vendaService.save(venda);
    }

    @GetMapping
    @RequestMapping(value = "/vendas/mensais/{ano}")
    public List<Float> ganhos(@PathVariable("ano") int ano){
        return vendaService.ganhosMensais(ano-1900);
    }

    @GetMapping
    @RequestMapping(value = "/vendas/tipo-pagamentos")
    public int[] payTypes(){
        int[] payTypeCount = {vendaService.vendasDinheiro().size(),
                vendaService.vendasPix().size(),
                vendaService.vendasCartao().size()};
        vendaService.pagamentoTypeCount();
        return payTypeCount;
    }
}
