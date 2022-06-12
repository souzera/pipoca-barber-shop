package com.br.pipoca.controller;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.repository.UsuarioRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping(value = "/usuarios")
    public List<Usuario> list() throws IOException {
        return repository.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public Usuario buscarUsuario(@PathVariable(value = "id") long id){
        return repository.findById(id);
    }

    @GetMapping(value = "/user/{login}")
    public Usuario buscarUsuario(@PathVariable(value = "login") String login){
        return repository.findByLogin(login);
    }

    @PostMapping(value = "/user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUsuario(@RequestBody @NotNull Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
    }
    @DeleteMapping(value = "/user/del")
    public void deletarUsuario(@RequestBody Usuario usuario){
        repository.delete(usuario);
    }

    @DeleteMapping(value = "/user/del/{id}")
    public void deletarPorID(@PathVariable(value = "id") long id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/user/att")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Usuario atualizarUsuario(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @GetMapping(value = "/user/validation")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String senha){
        Usuario tempUsuario = repository.findByLogin(login);
        if (tempUsuario != null){
            boolean valido = false;
            Usuario usuario = tempUsuario;
            valido = encoder.matches(senha, usuario.getSenha());
            HttpStatus status = (valido) ? HttpStatus.OK:HttpStatus.UNAUTHORIZED;
            return ResponseEntity.status(status).body(valido);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    }
}

