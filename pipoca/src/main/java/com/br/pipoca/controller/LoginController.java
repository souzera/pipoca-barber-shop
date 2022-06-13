package com.br.pipoca.controller;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.repository.UsuarioRepository;
import com.br.pipoca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @RequestMapping(value = "/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login.html");
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/logar")
    public String logar(Model model, String login, String senha, String manterLogin){
        Usuario usuario = usuarioService.findByLogin(login);
        if (usuario!=null){
            return "redirect:/usuario/validation?login=" + login + "&senha=" + senha;
        }
        model.addAttribute("erro", "Usuário ou senha Inválidos");
        return "login/login";

    }

}
