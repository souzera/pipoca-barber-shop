package com.br.pipoca.controller;

import com.br.pipoca.dto.ClienteDTO;
import com.br.pipoca.entity.Cliente;
import com.br.pipoca.service.ClienteService;
import com.br.pipoca.service.CookieService;
import com.br.pipoca.service.FuncionarioService;
import com.br.pipoca.service.UsuarioService;
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


    //========================================================================================

    //CLIENTES

    @GetMapping
    @RequestMapping("/cliente/alterar{id}")
    public ModelAndView clienteAlterar(@RequestParam(value = "id") long id, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/alterarCliente.html");
        modelAndView.addObject("cliente", clienteService.buscarPorID(id));
        modelAndView.addObject("usuario", usuarioService.findByLogin(CookieService.getCookieName(request, "login")));
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

    //========================================================================================
    //========================================================================================

    //USUARIO

    //========================================================================================


}
