package com.br.pipoca.service;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> list() throws IOException {
        Iterable<Usuario> usuarioIterable = this.usuarioRepository.findAll();
        return Streamable.of(usuarioIterable).toList();
    }


    public Usuario findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }
}
