package com.br.pipoca.controller;

import com.br.pipoca.dto.ClienteDTO;
import com.br.pipoca.dto.UsuarioDTO;
import com.br.pipoca.entity.Cliente;
import com.br.pipoca.entity.Usuario;
import com.br.pipoca.service.ClienteService;
import com.br.pipoca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @RequestMapping(value = "/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView modelAndView = new ModelAndView("cliente/cadastro.html");
        return modelAndView;
    }

    @PostMapping(value = "/cadastrando")
    public String create(UsuarioDTO usuarioDTO, ClienteDTO clienteDTO, Model model){
        if (usuarioService.isPresent(usuarioDTO.getLogin())){
            model.addAttribute("erro", "Este usuário já existe.");
            return "cliente/cadastro";
        }
        if(!(usuarioDTO.getSenha().equals(usuarioDTO.getConfirmaSenha()))){
            model.addAttribute("erro", "As senhas devem ser correspondentes");
            return "cliente/cadastro";
        }
        Usuario usuario = usuarioDTO.toUsuarioCliente();
        Cliente cliente = clienteDTO.toCliente();
        usuarioService.criptarSenha(usuario);
        usuarioService.saveUsuario(usuario);
        cliente.setUsuario(usuarioService.findByLogin(usuario.getLogin()));
        clienteService.saveCliente(cliente);
        return "redirect:/home";
    }

}
