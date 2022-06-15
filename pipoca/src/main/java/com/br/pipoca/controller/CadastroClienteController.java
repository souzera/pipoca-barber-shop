package com.br.pipoca.controller;

import com.br.pipoca.dto.ClienteDTO;
import com.br.pipoca.entity.Cliente;
import com.br.pipoca.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(value = "/cadastrar-se")
    public ModelAndView cadastrarse(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cliente/cadastro-cliente.html");
        return modelAndView;
    }

    @PostMapping(value = "/cadastrando")
    public void create(@RequestBody ClienteDTO clienteDTO){
        System.out.println(clienteDTO);
        //Cliente cliente = clienteDTO.toCliente();
        //clienteRepository.save(cliente);
        //return "redirect:/home";
    }
}
