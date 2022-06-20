package com.br.pipoca.controller;

import com.br.pipoca.dto.ClienteDTO;
import com.br.pipoca.dto.FuncionarioDTO;
import com.br.pipoca.dto.ProdutoDTO;
import com.br.pipoca.dto.UsuarioDTO;
import com.br.pipoca.entity.Cliente;
import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Produto;
import com.br.pipoca.entity.Usuario;
import com.br.pipoca.service.ClienteService;
import com.br.pipoca.service.FuncionarioService;
import com.br.pipoca.service.ProdutoService;
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
    @Autowired
    ClienteService clienteService;
    @Autowired
    ProdutoService produtoService;

    @GetMapping
    @RequestMapping(value = "/cadastrar/funcionario")
    public ModelAndView cadastrarFuncionario(){
        ModelAndView modelAndView = new ModelAndView("admin/cadastroFuncionario.html");
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/cadastrar/cliente")
    public ModelAndView cadastrarCliente(){
        ModelAndView modelAndView = new ModelAndView("admin/cadastroCliente.html");
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/registro/produto")
    public ModelAndView cadastrarProduto(){
        ModelAndView modelAndView = new ModelAndView("admin/cadastroProduto.html");
        return modelAndView;
    }

    @GetMapping
    @RequestMapping(value = "/registro/servico")
    public ModelAndView cadastrarServico(){
        ModelAndView modelAndView = new ModelAndView("admin/cadastroProduto.html");
        return modelAndView;
    }

    @PostMapping(value = "/cadastrando/funcionario")
    public String createFuncionario(UsuarioDTO usuarioDTO, FuncionarioDTO funcionarioDTO, Model model){
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

    @PostMapping(value = "/cadastrando/cliente")
    public String createCliente(UsuarioDTO usuarioDTO, ClienteDTO clienteDTO, Model model){
        if (usuarioService.isPresent(usuarioDTO.getLogin())){
            model.addAttribute("erro", "Este usuário já existe.");
            return "admin/cadastroCliente";
        }
        if(!(usuarioDTO.getSenha().equals(usuarioDTO.getConfirmaSenha()))){
            model.addAttribute("erro", "As senhas devem ser correspondentes");
            return "admin/cadastroCliente";
        }
        Usuario usuario = usuarioDTO.toUsuarioCliente();
        usuarioService.criptarSenha(usuario);
        usuarioService.saveUsuario(usuario);
        Cliente cliente = clienteDTO.toCliente();
        cliente.setUsuario(usuarioService.findByLogin(usuario.getLogin()));
        clienteService.saveCliente(cliente);
        return "redirect:/home";
    }

    @PostMapping(value = "/registrando/produto")
    public String createProduto(ProdutoDTO produtoDTO){
        Produto produto = produtoDTO.toProduto();
        produtoService.saveProduto(produto);
        return "redirect:/registro/produto";
    }
}
