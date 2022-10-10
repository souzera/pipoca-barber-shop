package com.br.pipoca.controller;

import com.br.pipoca.dto.AtendimentosFuncionarioDiariosDTO;
import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Usuario;
import com.br.pipoca.service.AtendimentoService;
import com.br.pipoca.service.CookieService;
import com.br.pipoca.service.FuncionarioService;
import com.br.pipoca.service.UsuarioService;
import com.br.pipoca.util.Cargo;
import com.br.pipoca.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GraficosController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    AtendimentoService atendimentoService;

    @GetMapping
    @RequestMapping("/graficos")
    public ModelAndView graficos(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/graficos");
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);

        switch (u.getTipoUsuario()){
            case ADM:
            case DEV:
            case SUPER:
            case ATENDENTE:
                //Note: ESTA LISTAGEM É ESTATICA
                //TODO: TENTAR FAZER DE FORMA DINAMICA

                List<AtendimentosFuncionarioDiariosDTO> barbeirosAtendimentos = new ArrayList<>();

                List<Funcionario> barbeiros = funcionarioService.findByCargo(Cargo.BARBEIRO);

                for (Funcionario barber: barbeiros){
                    barbeirosAtendimentos.add(new AtendimentosFuncionarioDiariosDTO(barber,
                            atendimentoService.agendamentosDateEFuncionario(barber, DateConverter.dateConverter(new java.util.Date()))));

                }
                modelAndView.addObject("barbeirosTable",  barbeirosAtendimentos);
                break;
            case BARBEIRO:
                break;

        }
        return modelAndView;
    }
}
