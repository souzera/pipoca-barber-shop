package com.br.pipoca.controller;

import com.br.pipoca.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.pipoca.service.UsuarioService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @RequestMapping(value = "/dashboard/{chave}")
    public ModelAndView dashboard(@PathVariable int chave,
                                  HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        if (CookieService.getCookieName(request,"login")!=null){
            modelAndView.setViewName("admin/admin.html");
            if (usuarioService.findByLogin(CookieService.getCookieValue(request,"login"))
                    .getTipoUsuario().getValor() == 4){
                //criar pagina de usuario não autorizado
                modelAndView.setViewName("admin/user-unauthorized.html");
                return modelAndView;
            }
            modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieValue(request,"login")));
            return modelAndView;
        }
        response.sendRedirect("/login");
        return null;
    }

    @GetMapping
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (CookieService.getCookieName(request, "login") != null){
            Cookie c = CookieService.getCookie(request, "login");
            c.setMaxAge(0);
            response.addCookie(c);
        }
        return "redirect:/login";
    }

}
