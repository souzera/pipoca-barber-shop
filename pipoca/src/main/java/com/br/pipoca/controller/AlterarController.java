package com.br.pipoca.controller;

import com.br.pipoca.dto.ClienteDTO;
import com.br.pipoca.entity.*;
import com.br.pipoca.service.*;
import com.br.pipoca.util.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class AlterarController {

    @Autowired
    ClienteService clienteService;
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ProdutoService produtoService;
    @Autowired
    ServicoService servicoService;


    //========================================================================================

    //CLIENTES

    @GetMapping
    @RequestMapping("/cliente/alterar{id}")
    public ModelAndView clienteAlterar(@RequestParam(value = "id") long id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/alterarCliente.html");
        modelAndView.addObject("cliente", clienteService.buscarPorID(id));
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }

    @PostMapping
    @RequestMapping("cliente/alterando")
    public String alterandoCliente(Cliente cliente){
        Cliente c = clienteService.buscarPorID(cliente.getId());
        c.setNome(cliente.getNome());
        c.setSexo(cliente.getSexo());
        c.setDtNascimento(cliente.getDtNascimento());
        c.setTelefone(cliente.getTelefone());
        clienteService.saveCliente(c);
        return "redirect:/cliente/details?id="+c.getId();
    }

    //========================================================================================
    //========================================================================================

    //FUNCIONARIOS

    @GetMapping
    @RequestMapping("/funcionario/alterar{id}")
    public ModelAndView funcionarioAlterar(@RequestParam(value = "id") long id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/alterarFuncionario.html");
        modelAndView.addObject("funcionario", funcionarioService.findById(id));
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }

    @PostMapping
    @RequestMapping("funcionario/alterando")
    public String alterandoFucionario(Funcionario funcionario){
        Funcionario f = funcionarioService.findById(funcionario.getId());
        f.setNome(funcionario.getNome());
        f.setSexo(funcionario.getSexo());
        f.setDtNascimento(funcionario.getDtNascimento());
        f.setTelefone(funcionario.getTelefone());
        f.setCargo(funcionario.getCargo());
        funcionarioService.saveFuncionario(f);
        return "redirect:/funcionario/details?id="+f.getId();
    }

    //========================================================================================
    //========================================================================================

    //USUARIO

    //========================================================================================
    //========================================================================================

    //PRODUTO

    @GetMapping
    @RequestMapping("/produto/alterar{id}")
    public ModelAndView produtoAlterar(@RequestParam(value = "id") long id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/alterarProduto.html");
        modelAndView.addObject("produto", produtoService.findById(id));
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }

    @PostMapping
    @RequestMapping("produto/alterando")
    public String alterandoProduto(Produto produto){
        Produto p = produtoService.findById(produto.getId());
        p.setDescricao(produto.getDescricao());
        p.setCategoria(produto.getCategoria());
        p.setMarca(produto.getMarca());
        p.setValor(produto.getValor());
        produtoService.saveProduto(p);
        return "redirect:/produto/details?id="+p.getId();
    }

    //========================================================================================
    //========================================================================================

    //SERVIÇO

    @GetMapping
    @RequestMapping("/servico/alterar{id}")
    public ModelAndView servicoAlterar(@RequestParam(value = "id") long id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/alterarServico.html");
        modelAndView.addObject("servico", servicoService.findById(id));
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }

    @PostMapping
    @RequestMapping("servico/alterando")
    public String alterandoServico(Servico servico){
        Servico s = servicoService.findById(servico.getId());
        s.setDescricao(servico.getDescricao());
        s.setValor(servico.getValor());
        servicoService.saveServico(s);
        return "redirect:/servico/details?id="+s.getId();
    }

    //========================================================================================


}
