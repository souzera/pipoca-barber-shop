package com.br.pipoca.controller;

import com.br.pipoca.service.CookieService;
import com.br.pipoca.service.FuncionarioService;
import com.br.pipoca.service.UsuarioService;
import com.br.pipoca.util.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@Controller
public class BuscasController {

    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    @RequestMapping(value = "/busca/agendamento")
    public ModelAndView buscaGeral(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/buscaAgenda.html");
        modelAndView.addObject("funcionarios", funcionarioService.findByCargo(Cargo.BARBEIRO));
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request, "login")), modelAndView);
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/buscando/agendamento-data")
    public void buscaAgendaData(Date date, HttpServletResponse response) throws IOException {
        response.sendRedirect("/agendamento/date/"+ date);
    }

    @PostMapping
    @RequestMapping(value = "/buscando/agendamento-funcionario")
    public void buscaAgendaData(long funcionario_id, HttpServletResponse response) throws IOException {
        response.sendRedirect("/agendamento/funcionario/"+ funcionario_id);
    }

}
