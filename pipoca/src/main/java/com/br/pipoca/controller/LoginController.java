package com.br.pipoca.controller;

import com.br.pipoca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        if (usuarioService.validarSenha(login, senha)){
            int tipoConta = usuarioService.findByLogin(login).getTipoUsuario().getValor();
            switch (tipoConta){
                case 0:
                    return "redirect:/dashboard/" + login + "/" + usuarioService.getChave(login);
                case 1:
                    return "redirect:/dashboard/" + login + "/" + usuarioService.getChave(login);
                case 2:
                    return "redirect:/dashboard/" + login + "/" + usuarioService.getChave(login);
                case 3:
                    return "redirect:/dashboard/" + login + "/" + usuarioService.getChave(login);
                case 4:
                    return "redirect:/home";
                case 5:
                    return "redirect:/dashboard/" + login + "/" + usuarioService.getChave(login);
            }
        }
        model.addAttribute("erro", "Usuário ou senha Inválidos");
        return "redirect:/login";
    }

}
