package com.br.pipoca.controller;

import com.br.pipoca.entity.*;
import com.br.pipoca.service.*;
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
    @Autowired private HorarioService horarioService;
    @Autowired private AtendimentoService atendimentoService;

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

    @GetMapping
    @RequestMapping(value = "/agendamentos")
    public ModelAndView listaHorarios() throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        List<Atendimento> agendamentos = atendimentoService.agendamentos();
        System.out.println(agendamentos);
        modelAndView.addObject("lista",agendamentos);

        return modelAndView;
    }
}
