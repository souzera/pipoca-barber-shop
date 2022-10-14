package com.br.pipoca.controller;

import com.br.pipoca.dto.AtendimentosFuncionarioDiariosDTO;
import com.br.pipoca.dto.ListaMetodosPagamentoDTO;
import com.br.pipoca.dto.ListaVendaFuncionarioDTO;
import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Usuario;
import com.br.pipoca.service.*;
import com.br.pipoca.util.Cargo;
import com.br.pipoca.util.DateConverter;
import com.br.pipoca.util.Pagamento;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
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

    @Autowired
    VendaService vendaService;

    @GetMapping
    @RequestMapping("/graficos")
    public ModelAndView graficos(HttpServletRequest request) throws IOException {
        Date hoje = DateConverter.dateConverter(new java.util.Date());

        ModelAndView modelAndView = new ModelAndView("admin/graficos");
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        modelAndView.addObject("hoje", hoje.getDate() + "/" + hoje.getMonth() + "/" + hoje.getYear());
        usuarioService.addPass(u, modelAndView);

        switch (u.getTipoUsuario()){
            case ADM:
            case DEV:
            case SUPER:
            case ATENDENTE:
                //Note: ESTA LISTAGEM É ESTATICA
                //TODO: TENTAR FAZER DE FORMA DINAMICA - ok
                List<AtendimentosFuncionarioDiariosDTO> barbeirosAtendimentos = new ArrayList<>();
                List<Funcionario> barbeiros = funcionarioService.findByCargo(Cargo.BARBEIRO);

                for (Funcionario barber: barbeiros){
                    barbeirosAtendimentos.add(new AtendimentosFuncionarioDiariosDTO(barber,
                            atendimentoService.agendamentosDateEFuncionario(barber, hoje)));

                }
                modelAndView.addObject("barbeirosTable",  barbeirosAtendimentos);
                // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- \\

                List<ListaMetodosPagamentoDTO> listaMetodosPag = new ArrayList<>();
                //Venda em Dinheiro Diarias
                listaMetodosPag.add(new ListaMetodosPagamentoDTO("Dinheiro",
                                vendaService.vendasPorPagTypeDate(Pagamento.DINHEIRO,hoje)));
                //Venda em Cartão Diarias
                listaMetodosPag.add(new ListaMetodosPagamentoDTO("Cartão",
                        vendaService.vendasPorPagTypeDate(Pagamento.CARTAO, hoje)));
                //Venda em Pix Diarias
                listaMetodosPag.add(new ListaMetodosPagamentoDTO("Pix",
                        vendaService.vendasPorPagTypeDate(Pagamento.PIX, hoje)));

                modelAndView.addObject("tableMetPag",listaMetodosPag);

                // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- \\

                List<ListaVendaFuncionarioDTO> listaVendaFunc = new ArrayList<>();

                for (Funcionario barber: barbeiros){
                    listaVendaFunc.add(new ListaVendaFuncionarioDTO(barber,
                            vendaService.vendasAgendaByDateFuncionario(hoje, barber)));
                }
                modelAndView.addObject("tableVendaFunc", listaVendaFunc);

                // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- \\

                break;
            case BARBEIRO:
                break;

        }
        return modelAndView;
    }
}
