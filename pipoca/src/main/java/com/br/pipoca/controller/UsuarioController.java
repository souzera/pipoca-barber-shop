package com.br.pipoca.controller;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.model.TipoUsuario;
import com.br.pipoca.repository.UsuarioRepository;
import com.br.pipoca.service.UsuarioService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    private final PasswordEncoder encoder;

    public UsuarioController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @GetMapping(value = "/usuarios")
    public List<Usuario> list() throws IOException {
        return usuarioService.list();
    }

    @GetMapping(value = "/usuario/{id}")
    public Usuario buscarUsuario(@PathVariable(value="id") long id){
        return usuarioService.findById(id);
    }

    @GetMapping(value = "/{login}")
    public Usuario buscarLogin(@PathVariable(value="login") String login){
        return usuarioService.findByLogin(login);
    }

    @PostMapping(value = "/usuario/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUsuario(@RequestBody @NotNull Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuarioService.getUsuarioRepository().save(usuario);
    }
    @DeleteMapping(value = "/usuario/del")
    public void deletarUsuario(@RequestBody Usuario usuario){
        usuarioService.getUsuarioRepository().delete(usuario);
    }

    @DeleteMapping(value = "/usuario/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        usuarioService.getUsuarioRepository().deleteById(id);
    }

    @PutMapping(value = "/usuario/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Usuario atualizarUsuario(@RequestBody Usuario usuario){
        return usuarioService.getUsuarioRepository().save(usuario);
    }

    @GetMapping(value = "/usuario/validation")
    public boolean validarSenha(@RequestParam String login, @RequestParam String senha){
        return usuarioService.validarSenha(login, senha);
    }
}

