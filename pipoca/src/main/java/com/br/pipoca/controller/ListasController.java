package com.br.pipoca.controller;

import com.br.pipoca.entity.*;
import com.br.pipoca.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
public class ListasController {

    @Autowired private ClienteService clienteService;
    @Autowired private FuncionarioService funcionarioService;
    @Autowired private ProdutoService produtoService;
    @Autowired private ServicoService servicoService;
    @Autowired private HorarioService horarioService;
    @Autowired private AtendimentoService atendimentoService;

    @Autowired private VendaService vendaService;

    //====================================================================================

    //CLIENTES
    @GetMapping
    @RequestMapping(value = "/clientes")
    public ModelAndView listarClientes() throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaCliente.html");

        List<Cliente> lista = clienteService.clientes();
        System.out.println(clienteService.getAniversario(lista.get(0)));
        modelAndView.addObject("lista",lista);
        return modelAndView;
    }

    //========================================================================================
    //FUNCIONARIOS

    @GetMapping
    @RequestMapping(value = "/funcionarios")
    public ModelAndView listarFuncionarios() throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaFuncionario.html");
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


    //===========================================================================================

    //AGENDAMENTOS

    @GetMapping
    @RequestMapping(value = "/agendamentos")
    public ModelAndView listaHorarios() throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        List<Atendimento> agendamentos = atendimentoService.ociosos();
        System.out.println(agendamentos);
        modelAndView.addObject("lista",agendamentos);

        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/agendamento/func_{funcionario_id}")
    public ModelAndView buscaFuncionario(@PathVariable("funcionario_id") long funcionario_id) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        List<Atendimento> agendamentos = atendimentoService.agendamentosFuncionario(funcionarioService.findById(funcionario_id));
        System.out.println(agendamentos);
        modelAndView.addObject("lista",agendamentos);

        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/agendamento/date_{data}")
    public ModelAndView buscaData(@PathVariable("data")Date date) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        List<Atendimento> agendamentos = atendimentoService.agendamentosDate(date);
        System.out.println(agendamentos);
        modelAndView.addObject("lista",agendamentos);

        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/agendamento/date_{data}&func_{funcionario_id}")
    public ModelAndView buscaDataEFunc(@PathVariable("data")Date date, @PathVariable("funcionario_id") long funcionario_id) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        List<Atendimento> agendamentos = atendimentoService.agendamentosDateEFuncionario(funcionarioService.findById(funcionario_id), date);
        System.out.println(agendamentos);
        modelAndView.addObject("lista",agendamentos);

        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/agendamentos/finalizados")
    public ModelAndView agendamentosFinalizados() throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        List<Atendimento> agendamentos = atendimentoService.finalizados();
        System.out.println(agendamentos);
        modelAndView.addObject("lista",agendamentos);

        return modelAndView;
    }

}
