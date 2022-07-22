package com.br.pipoca.controller;

import com.br.pipoca.dto.ClienteDTO;
import com.br.pipoca.entity.*;
import com.br.pipoca.service.*;
import com.br.pipoca.util.DateConverter;
import com.br.pipoca.util.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;

@Controller
public class DetalhesController {

    @Autowired
    AtendimentoService atendimentoService;
    @Autowired
    HorarioService horarioService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    VendaService vendaService;
    //=======================================================================================================

    //AGENDAMENTOS

    @GetMapping
    @RequestMapping(value = "/agenda/details{id}")
    public ModelAndView agendaDetails(@RequestParam(value = "id") long agenda_id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/agendamentoDetails.html");
        modelAndView.addObject("agendamento", atendimentoService.buscarPorId(agenda_id));
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieName(request, "login")));
        return modelAndView;
    }
    @PostMapping
    @RequestMapping(value = "/agenda/deletar")
    public String deletarAgenda(long agenda_id){
        long horario_id = atendimentoService.buscarPorId(agenda_id).getHorario().getId();
        atendimentoService.deleteById(agenda_id);
        horarioService.deleteById(horario_id);
        return "redirect:/agendamentos";
    }
    @PostMapping
    @RequestMapping(value = "/agenda/check")
    public String concluirAgenda(long agenda_id, Pagamento pagamento){
        Horario h = atendimentoService.buscarPorId(agenda_id).getHorario();
        horarioService.alterarStatus(h, 0);
        Venda venda = new Venda();
        venda.setAtendimento(atendimentoService.buscarPorId(agenda_id));
        venda.setValor(venda.getAtendimento().getServico().getValor());
        venda.setPagamento(pagamento);
        venda.setDate(DateConverter.dateConverter(new java.util.Date()));
        vendaService.save(venda);
        return "redirect:/agendamentos";
    }

    //=======================================================================================================
    //=======================================================================================================

    //CLIENTES & FUNCIONARIO

    //cliente
    @GetMapping
    @RequestMapping("/cliente/details{id}")
    public ModelAndView clienteDetails(@RequestParam(value = "id") long id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/clienteDetails.html");
        modelAndView.addObject("cliente", clienteService.buscarPorID(id));
        modelAndView.addObject("agendamentos", atendimentoService.agendamentosCliente(id));
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieName(request, "login")));
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/cliente/deletar")
    public String deletarCliente(long id){
        Cliente c = clienteService.buscarPorID(id);
        for (Atendimento a: atendimentoService.agendamentosCliente(id)) {
            horarioService.deletarHorario(a.getHorario());
            atendimentoService.deleteById(a.getId());
        }
        clienteService.deleteById(id);
        return "redirect:/clientes";
    }

    //funcionario
    @GetMapping
    @RequestMapping("/funcionario/details{id}")
    public ModelAndView funcionarioDetails(@RequestParam(value = "id") long id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/funcionarioDetails.html");
        modelAndView.addObject("funcionario", funcionarioService.findById(id));
        modelAndView.addObject("agendamentos", atendimentoService.agendamentosFuncionario(id));
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieName(request, "login")));
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/funcionario/deletar")
    public String deletarFuncionario(long id) throws IOException {
        Funcionario f = funcionarioService.findById(id);
        for (Atendimento a: atendimentoService.agendamentosFuncionario(id)) {
            horarioService.deletarHorario(a.getHorario());
            atendimentoService.deleteById(a.getId());
        }
        funcionarioService.deleteById(id);
        return "redirect:/agendamentos";
    }

    //=======================================================================================================
    //=======================================================================================================

    //VENDA

    //=======================================================================================================
    //=======================================================================================================

}
