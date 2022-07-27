package com.br.pipoca.controller;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.service.CookieService;
import com.br.pipoca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GraficosController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    @RequestMapping("/graficos")
    public ModelAndView graficos(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/graficos");
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }
}
