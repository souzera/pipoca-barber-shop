package com.br.pipoca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.pipoca.service.UsuarioService;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @RequestMapping(value = "/dashboard/{login}{id}/{chave}")
    public ModelAndView dashboard(@PathVariable String login, @PathVariable int chave){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin.html");
        if (usuarioService.findByLogin(login).getTipoUsuario().getValor() == 4){
            //criar pagina de usuario não autorizado
            modelAndView.setViewName("admin/user-unauthorized.html");
        }
        return modelAndView;
    }

}
