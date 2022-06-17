package com.br.pipoca.controller;

import com.br.pipoca.dto.ClienteDTO;
import com.br.pipoca.service.ClienteService;
import com.br.pipoca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @RequestMapping(value = "/client/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView modelAndView = new ModelAndView("cliente/cadastro.html");
        return modelAndView;
    }

    @PostMapping(value = "/client/cadastrando")
    public void create(@RequestBody ClienteDTO clienteDTO){
        System.out.println(clienteDTO);
    }

}
