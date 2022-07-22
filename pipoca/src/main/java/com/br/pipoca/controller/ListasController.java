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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
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
    @Autowired private UsuarioService usuarioService;

    //====================================================================================

    //CLIENTES
    @GetMapping
    @RequestMapping(value = "/clientes")
    public ModelAndView listarClientes(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaCliente.html");

        List<Cliente> lista = clienteService.clientes();
        modelAndView.addObject("lista",lista);
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        return modelAndView;
    }

    //========================================================================================
    //FUNCIONARIOS

    @GetMapping
    @RequestMapping(value = "/funcionarios")
    public ModelAndView listarFuncionarios(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaFuncionario.html");
        List<Funcionario> lista = funcionarioService.funcionarios();
        System.out.println(lista.get(0).getNome());
        modelAndView.addObject("lista",lista);
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/produtos")
    public ModelAndView listaProdutos(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaProduto.html");

        List<Produto> lista = produtoService.produtos();
        System.out.println(lista);
        modelAndView.addObject("lista",lista);
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/servicos")
    public ModelAndView listaServicos(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaServicos.html");

        List<Servico> lista = servicoService.servicos();
        System.out.println(lista);
        modelAndView.addObject("lista",lista);
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        return modelAndView;
    }


    //===========================================================================================

    //AGENDAMENTOS

    @GetMapping
    @RequestMapping(value = "/agendamentos")
    public ModelAndView listaHorarios(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        switch (u.getTipoUsuario()){
            case ADM:
            case DEV:
            case SUPER:
            case FUNCIONARIO:
                modelAndView.addObject("lista",atendimentoService.ociosos());
                break;
            case BARBEIRO:
                modelAndView.addObject("lista",atendimentoService.ociososFuncionario(funcionarioService.findByLogin(u.getLogin()).getId()));
                break;
        }
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/agendamento/funcionario_id={funcionario_id}")
    public ModelAndView buscaFuncionario(@PathVariable("funcionario_id") long funcionario_id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        Funcionario f = funcionarioService.findById(funcionario_id);
        List<Atendimento> agendamentos = atendimentoService.agendamentosFuncionario(funcionario_id);
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        System.out.println(agendamentos);
        modelAndView.addObject("lista",agendamentos);

        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/agendamento/date={data}")
    public ModelAndView buscaData(@PathVariable("data")Date date, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        Date d = date;
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        switch (u.getTipoUsuario()){
            case ADM:
            case DEV:
            case SUPER:
            case FUNCIONARIO:
                modelAndView.addObject("lista",atendimentoService.ociosos());
                break;
            case BARBEIRO:
                modelAndView.addObject("lista",atendimentoService.ociososFuncionario(funcionarioService.findByLogin(u.getLogin()).getId()));
                break;
        }
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/agendamento/{data}/{funcionario_id}")
    public ModelAndView buscaDataEFunc(@PathVariable("data")Date date, @PathVariable("funcionario_id") long funcionario_id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        switch (u.getTipoUsuario()){
            case ADM:
            case DEV:
            case SUPER:
            case FUNCIONARIO:
                modelAndView.addObject("lista",atendimentoService.agendamentosDateEFuncionario(funcionarioService.findById(funcionario_id),date));
                break;
            case BARBEIRO:
                modelAndView.addObject("lista",atendimentoService.agendamentosDateEFuncionario(funcionarioService.findByLogin(u.getLogin()),date));
                break;
        }
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/agendamentos/finalizados")
    public ModelAndView agendamentosFinalizados(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/listaAgendamento.html");
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        switch (u.getTipoUsuario()){
            case ADM:
            case DEV:
            case SUPER:
            case FUNCIONARIO:
                modelAndView.addObject("lista",atendimentoService.agendamentos());
                break;
            case BARBEIRO:
                modelAndView.addObject("lista",atendimentoService.agendamentosFuncionario(funcionarioService.findByLogin(u.getLogin()).getId()));
                break;
        }
        return modelAndView;
    }

    //===========================================================================================

    //VENDA

    @GetMapping
    @RequestMapping(value = "/vendas")
    public ModelAndView vendas(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/listaVenda.html");
        List<Venda> vendas = vendaService.vendas();
        modelAndView.addObject("lista", vendas);
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/vendas/{mes}{ano}")
    public ModelAndView vendasMensais(@RequestParam("mes") int mes, @RequestParam("ano") int ano, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/listaVenda.html");
        List<Venda> vendas = vendaService.vendasMensais(mes,ano-1900);
        modelAndView.addObject("lista", vendas);
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/vendas/{ano}")
    public ModelAndView vendasAnuais(@PathVariable("ano") int ano, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/listaVenda.html");
        List<Venda> vendas = vendaService.vendasAnuais(ano-1900);
        modelAndView.addObject("lista", vendas);
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/vendas/servicos")
    public ModelAndView vendasServicos(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/listaVenda.html");
        List<Venda> vendas = vendaService.vendaServicos();
        modelAndView.addObject("lista", vendas);
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/vendas/produtos")
    public ModelAndView vendasProdutos(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/listaVenda.html");
        List<Venda> vendas = vendaService.vendaProduto();
        modelAndView.addObject("lista", vendas);
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        return modelAndView;
    }
}
