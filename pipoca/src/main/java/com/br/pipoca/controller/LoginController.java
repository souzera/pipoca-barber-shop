package com.br.pipoca.controller;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.service.CookieService;
import com.br.pipoca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (CookieService.getCookieName(request, "login") != null){
            response.sendRedirect("/dashboard/" + CookieService.getCookieName(request, "login").hashCode());
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("login/login.html");
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/logar")
    public String logar(Model model, String login, String senha, String manterLogin, HttpServletResponse response){
        if (usuarioService.validarSenha(login, senha)){
            Usuario usuario = usuarioService.findByLogin(login);
            int tempo = 3600;
            if (manterLogin != null){tempo = tempo*24;}
            CookieService.setCookie(response, "login", usuario.getLogin(), tempo);
            int tipoConta = usuario.getTipoUsuario().getValor();
            switch (tipoConta){
                case 0:
                    return "redirect:/dashboard/" + usuarioService.getChave(login);
                case 1:
                    return "redirect:/dashboard/" + usuarioService.getChave(login);
                case 2:
                    return "redirect:/dashboard/" + usuarioService.getChave(login);
                case 3:
                    return "redirect:/dashboard/" + usuarioService.getChave(login);
                case 4:
                    return "redirect:/home";
                case 5:
                    return "redirect:/dashboard/" + usuarioService.getChave(login);
            }
        }
        model.addAttribute("erro", "Usuário ou senha Inválidos");
        return "redirect:/login";
    }

}
