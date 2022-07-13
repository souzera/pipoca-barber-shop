package com.br.pipoca.controller;

import com.br.pipoca.entity.Horario;
import com.br.pipoca.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public String concluirAgenda(long agenda_id){
        Horario h = atendimentoService.buscarPorId(agenda_id).getHorario();
        horarioService.alterarStatus(h, 0);
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
        modelAndView.addObject("pessoa", clienteService.buscarPorID(id));
        modelAndView.addObject("agendamentos", atendimentoService.agendamentosCliente(id));
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieName(request, "login")));
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/cliente/deletar")
    public String deletarCliente(long cliente_id){
        System.out.println(clienteService.buscarPorID(cliente_id));
        clienteService.deleteById(cliente_id);
        return "redirect:/agendamentos";
    }

    //funcionario
    @GetMapping
    @RequestMapping("/funcionario/details{id}")
    public ModelAndView funcionarioDetails(@RequestParam(value = "id") long id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/funcionarioDetails.html");
        modelAndView.addObject("funcionario", funcionarioService.findById(id));
        modelAndView.addObject("agendamentos", atendimentoService.agendamentosFuncionario(funcionarioService.findById(id)));
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieName(request, "login")));
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/funcionario/deletar")
    public String deletarFuncionario(long funcionario_id){
        System.out.println(funcionarioService.findById(funcionario_id));
        funcionarioService.deleteById(funcionario_id);
        return "redirect:/agendamentos";
    }

    //=======================================================================================================
    //=======================================================================================================

    //alguma coisa

    //=======================================================================================================
    //=======================================================================================================

}
