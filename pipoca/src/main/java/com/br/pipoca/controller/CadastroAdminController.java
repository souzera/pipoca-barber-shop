package com.br.pipoca.controller;

import com.br.pipoca.dto.FuncionarioDTO;
import com.br.pipoca.dto.UsuarioDTO;
import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Usuario;
import com.br.pipoca.service.FuncionarioService;
import com.br.pipoca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroAdminController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping
    @RequestMapping(value = "/cadastrar/funcionario")
    public ModelAndView cadastrar(){
        ModelAndView modelAndView = new ModelAndView("admin/cadastroFuncionario.html");
        return modelAndView;
    }

    @PostMapping(value = "/cadastrando/funcionario")
    public String create(UsuarioDTO usuarioDTO, FuncionarioDTO funcionarioDTO, Model model){
        if (usuarioService.isPresent(usuarioDTO.getLogin())){
            model.addAttribute("erro", "Este usuário já existe.");
            return "admin/cadastroFuncionario";
        }
        if(!(usuarioDTO.getSenha().equals(usuarioDTO.getConfirmaSenha()))){
            model.addAttribute("erro", "As senhas devem ser correspondentes");
            return "admin/cadastroFuncionario";
        }
        Usuario usuario = usuarioDTO.toUsuarioCliente();
        usuarioService.criptarSenha(usuario);
        usuarioService.saveUsuario(usuario);
        Funcionario funcionario = funcionarioDTO.toFuncionario();
        funcionario.setUsuario(usuarioService.findByLogin(usuario.getLogin()));
        funcionarioService.saveFuncionario(funcionario);
        return "redirect:/dashboard/"+ funcionario.getUsuario().getLogin()+ "/" + funcionario.hashCode();
    }
}
