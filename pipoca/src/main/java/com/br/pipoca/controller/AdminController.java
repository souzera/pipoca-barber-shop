package com.br.pipoca.controller;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.service.*;
import com.br.pipoca.util.DateConverter;
import com.br.pipoca.util.Semana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VendaService vendaService;
    @Autowired
    AtendimentoService atendimentoService;
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    private ClienteService clienteService;

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
            Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
            modelAndView.addObject("usuario", u);
            usuarioService.addPass(u, modelAndView);
            System.out.println(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")).getClass().getName().toLowerCase());
            //===================================GRÁFICOS=========================================

            java.util.Date hoje = new java.util.Date();
            modelAndView.addObject("vendaMensal", vendaService.receitaMensal(hoje.getMonth(),hoje.getYear()));
            modelAndView.addObject("vendaAnual", vendaService.receitaAnual(hoje.getYear()));
            modelAndView.addObject("vendaDiaria", vendaService.receitaDiaria(DateConverter.dateConverter(hoje)));
            modelAndView.addObject("vendaSemanal", vendaService.receitaSemanal(DateConverter.dateConverter(hoje)));
            Semana.getSemana(DateConverter.dateConverter(hoje));
            //trocar quando implementar a fila
            modelAndView.addObject("fila", atendimentoService.ociosos().size());
            //====================================================================================
            //===================================LISTA=========================================
            modelAndView.addObject("aniversariantes", clienteService.aniversariantes());
            switch (u.getTipoUsuario()){
                case ADM:
                case DEV:
                case SUPER:
                case ATENDENTE:
                    modelAndView.addObject("lista", atendimentoService.agendamentosDate(DateConverter.dateConverter(hoje)));
                    modelAndView.addObject("progresso", atendimentoService.progressoDiario(DateConverter.dateConverter(hoje)));
                    break;
                case BARBEIRO:
                    modelAndView.addObject("lista", atendimentoService.ociososFuncionario(funcionarioService.findByLogin(u.getLogin()).getId()));
                    modelAndView.addObject("ociosos", atendimentoService.ociososFuncionario(funcionarioService.findByLogin(u.getLogin()).getId()).size());
                    modelAndView.addObject("concluidos", atendimentoService.concluidosFuncionario(funcionarioService.findByLogin(u.getLogin()).getId()).size());
                    modelAndView.addObject("progresso", atendimentoService.progressoFuncionario(funcionarioService.findByLogin(u.getLogin()).getId()));
                    modelAndView.addObject("hojeStr", ""+hoje.getDate()+"/"+hoje.getMonth());
                    break;
            }
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
