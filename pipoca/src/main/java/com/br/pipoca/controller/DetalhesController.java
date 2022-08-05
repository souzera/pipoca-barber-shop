package com.br.pipoca.controller;

import com.br.pipoca.dto.ClienteDTO;
import com.br.pipoca.entity.*;
import com.br.pipoca.service.*;
import com.br.pipoca.util.DateConverter;
import com.br.pipoca.util.Pagamento;
import com.br.pipoca.util.StatusHorario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;

@Controller
public class DetalhesController {

    @Autowired
    AtendimentoService atendimentoService;
    @Autowired
    HorarioService horarioService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    VendaService vendaService;
    @Autowired
    ProdutoService produtoService;
    @Autowired
    ServicoService servicoService;
    //=======================================================================================================

    //AGENDAMENTOS *horario

    @GetMapping
    @RequestMapping(value = "/agenda/details{id}")
    public ModelAndView agendaDetails(@RequestParam(value = "id") long agenda_id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/agendamentoDetails.html");
        modelAndView.addObject("agendamento", atendimentoService.buscarPorId(agenda_id));
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }
    @PostMapping
    @RequestMapping(value = "/agenda/deletar")
    public String deletarAgenda(long agenda_id){
        long horario_id = atendimentoService.buscarPorId(agenda_id).getHorario().getId();
        atendimentoService.deleteById(agenda_id);
        horarioService.deleteById(horario_id);
        return "redirect:/agendamentos";
    }
    @PostMapping
    @RequestMapping(value = "/agenda/check")
    public String concluirAgenda(long agenda_id, Pagamento pagamento){
        Horario h = atendimentoService.buscarPorId(agenda_id).getHorario();
        if (h.getStatusHorario() == StatusHorario.CONCLUIDO){return "redirect:/agendamentos";}
        horarioService.alterarStatus(h, 0);
        Venda venda = new Venda();
        venda.setAtendimento(atendimentoService.buscarPorId(agenda_id));
        venda.setValor(venda.getAtendimento().getServico().getValor());
        venda.setPagamento(pagamento);
        venda.setDate(DateConverter.dateConverter(new java.util.Date()));
        vendaService.save(venda);
        return "redirect:/agendamentos";
    }

    //=======================================================================================================
    //=======================================================================================================

    //CLIENTES & FUNCIONARIO

    //cliente
    @GetMapping
    @RequestMapping("/cliente/details{id}")
    public ModelAndView clienteDetails(@RequestParam(value = "id") long id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/clienteDetails.html");
        modelAndView.addObject("cliente", clienteService.buscarPorID(id));
        modelAndView.addObject("agendamentos", atendimentoService.agendamentosCliente(id));
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/cliente/deletar")
    public String deletarCliente(long id){
        Cliente c = clienteService.buscarPorID(id);
        for (Atendimento a: atendimentoService.agendamentosCliente(id)) {
            horarioService.deletarHorario(a.getHorario());
            atendimentoService.deleteById(a.getId());
        }
        clienteService.deleteById(id);
        return "redirect:/clientes";
    }

    //funcionario
    @GetMapping
    @RequestMapping("/funcionario/details{id}")
    public ModelAndView funcionarioDetails(@RequestParam(value = "id") long id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/funcionarioDetails.html");
        modelAndView.addObject("funcionario", funcionarioService.findById(id));
        modelAndView.addObject("agendamentos", atendimentoService.agendamentosFuncionario(id));
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/funcionario/deletar")
    public String deletarFuncionario(long funcionario_id) throws IOException {
        Funcionario f = funcionarioService.findById(funcionario_id);
        Usuario u = usuarioService.findByLogin(f.getUsuario().getLogin());
        for (Atendimento a: atendimentoService.agendamentosFuncionario(funcionario_id)) {
            atendimentoService.deleteById(a.getId());
            horarioService.deletarHorario(a.getHorario());
        }
        funcionarioService.deleteById(funcionario_id);
        usuarioService.deleteById(u.getId());
        return "redirect:/funcionarios";
    }

    //=======================================================================================================
    //=======================================================================================================

    //VENDA

    //=======================================================================================================
    //=======================================================================================================

    //PRODUTO

    @GetMapping
    @RequestMapping("/produto/details{id}")
    public ModelAndView produtoDetails(@RequestParam(value = "id") long produto_id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/produtoDetails.html");
        modelAndView.addObject("produto", produtoService.findById(produto_id));
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/produto/deletar")
    public String deletarProduto(long produto_id) throws IOException {
        Produto p = produtoService.findById(produto_id);
        //todo: se hover vendas desse produto o manter apenas inativo
        produtoService.deleteById(produto_id);
        return "redirect:/produtos";
    }

    @PostMapping
    @RequestMapping(value = "/produto/venda")
    public String venderProduto(long produto_id, Pagamento pagamento) throws IOException {
        Produto p = produtoService.findById(produto_id);
        Venda venda = new Venda();
        venda.setProduto(p);
        venda.setValor(venda.getProduto().getValor());
        venda.setPagamento(pagamento);
        venda.setDate(DateConverter.dateConverter(new java.util.Date()));
        vendaService.save(venda);
        return "redirect:/produtos";
    }

    //=======================================================================================================
    //=======================================================================================================

    //SERVIÇO

    @GetMapping
    @RequestMapping("/servico/details{id}")
    public ModelAndView servicoDetails(@RequestParam(value = "id") long servico_id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/servicoDetails.html");
        modelAndView.addObject("servico", servicoService.findById(servico_id));
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = "/servico/deletar")
    public String deletarServico(long servico_id) throws IOException {
        Servico s = servicoService.findById(servico_id);
        //todo: se hover vendas desse servico o manter apenas inativo
        servicoService.deleteById(servico_id);
        return "redirect:/servicos";
    }

    //=======================================================================================================
    //=======================================================================================================

    //USUARIO

    //=======================================================================================================
    //=======================================================================================================

}
