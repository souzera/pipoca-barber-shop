package com.br.pipoca.controller;

import com.br.pipoca.service.AtendimentoService;
import com.br.pipoca.service.FuncionarioService;
import com.br.pipoca.service.HorarioService;
import com.br.pipoca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Date;

@Controller
public class FerramentasController {

    @Autowired FuncionarioService funcionarioService;
    @Autowired UsuarioService usuarioService;
    @Autowired HorarioService horarioService;
    @Autowired
    AtendimentoService atendimentoService;

    @GetMapping(value = "/abrir/agenda")
    public ModelAndView abrirAgenda() throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/abrirAgenda.html");
        modelAndView.addObject("funcionario", funcionarioService.funcionarios());
        return modelAndView;
    }

    @PostMapping(value = "/abrindo/agenda")
    public String abrindoAgenda(@DateTimeFormat(pattern = "yyyy-MM-dd") Date dia, long funcionario_id, int hora){
        return "redirect:/api/horarios";
    }
}
