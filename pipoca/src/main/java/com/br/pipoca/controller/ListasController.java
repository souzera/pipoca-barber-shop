package com.br.pipoca.controller;

import com.br.pipoca.entity.Cliente;
import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Produto;
import com.br.pipoca.entity.Servico;
import com.br.pipoca.service.ClienteService;
import com.br.pipoca.service.FuncionarioService;
import com.br.pipoca.service.ProdutoService;
import com.br.pipoca.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class ListasController {

    @Autowired private ClienteService clienteService;
    @Autowired private FuncionarioService funcionarioService;
    @Autowired private ProdutoService produtoService;
    @Autowired private ServicoService servicoService;

    @GetMapping
    @RequestMapping(value = "/clientes")
    public ModelAndView listarClientes() throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaPessoa.html");

        List<Cliente> lista = clienteService.clientes();
        System.out.println(lista);
        modelAndView.addObject("lista",lista);
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/funcionarios")
    public ModelAndView listarFuncionarios() throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaPessoa.html");

        List<Funcionario> lista = funcionarioService.funcionarios();
        System.out.println(lista.get(0).getNome());

        modelAndView.addObject("lista",lista);

        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/produtos")
    public ModelAndView listaProdutos() throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaProduto.html");

        List<Produto> lista = produtoService.produtos();
        System.out.println(lista);
        modelAndView.addObject("lista",lista);

        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/servicos")
    public ModelAndView listaServicos() throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaServicos.html");

        List<Servico> lista = servicoService.servicos();
        System.out.println(lista);
        modelAndView.addObject("lista",lista);

        return modelAndView;
    }
}
