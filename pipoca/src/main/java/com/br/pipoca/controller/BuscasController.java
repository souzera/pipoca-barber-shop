package com.br.pipoca.controller;

import com.br.pipoca.service.ClienteService;
import com.br.pipoca.service.CookieService;
import com.br.pipoca.service.FuncionarioService;
import com.br.pipoca.service.UsuarioService;
import com.br.pipoca.util.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /*@PostMapping
    @RequestMapping(value = "/buscando/agendamento")
    public String agendamentosRedirect(Date date, Long funcionario_id, Model model, HttpServletResponse response) throws IOException {
        if (date != null && funcionario_id != null){
            return "redirect:/agendamento/"+ date.toString() + "/"+ funcionario_id;
        } else if (!(date!=null)) {
            return "redirect:/agendamento/funcionario_id="+funcionario_id;
        } else if (!(funcionario_id !=null)){
            return "redirect:/agendamento/date="+ date;
        } else if (!(date!=null) && !(funcionario_id !=null)){
            model.addAttribute("erro", "Preencha ao menos 1 campo.");
            response.sendRedirect("/busca/agendamento");
        }
        return "redirect:/agendamentos";
    }*/
}
