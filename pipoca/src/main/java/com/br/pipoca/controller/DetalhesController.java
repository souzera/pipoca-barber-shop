package com.br.pipoca.controller;

import com.br.pipoca.service.AtendimentoService;
import com.br.pipoca.service.CookieService;
import com.br.pipoca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DetalhesController {

    @Autowired
    AtendimentoService atendimentoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    @RequestMapping(value = "/agenda/details{id}")
    public ModelAndView agendaDetails(@RequestParam(value = "id") long agenda_id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/agendamentoDetails.html");
        modelAndView.addObject("agendamento", atendimentoService.buscarPorId(agenda_id));
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieName(request, "login")));
        return modelAndView;
    }
}
