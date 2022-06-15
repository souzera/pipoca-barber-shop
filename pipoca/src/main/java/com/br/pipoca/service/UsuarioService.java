package com.br.pipoca.service;

import com.br.pipoca.entity.Usuario;
import com.br.pipoca.model.TipoUsuario;
import com.br.pipoca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<Usuario> list() throws IOException {
        Iterable<Usuario> usuarioIterable = this.usuarioRepository.findAll();
        return Streamable.of(usuarioIterable).toList();
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public Usuario findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public int getChave(String login){
        return findByLogin(login).hashCode();
    }
    public boolean validarSenha(String login, String senha){
        Usuario tempUsuario = usuarioRepository.findByLogin(login);
        boolean valido = false;
        if (tempUsuario != null){
            Usuario usuario = tempUsuario;
            valido = encoder.matches(senha, usuario.getSenha());
        }
        return valido;
    }

    public boolean dasboardAcessValidator(String login, int chave){
        if (isPresent(login) && chaveValidator(chave, login)){
            return true;
        }return false;
    }

    public boolean chaveValidator(int chave, String login){
        return (chave == findByLogin(login).hashCode()) ? true:false;
    }

    public boolean isPresent(String login){
        return (findByLogin(login) != null)? true:false;
    }

    public Usuario findById(long id) {
        return usuarioRepository.findById(id);
    }

    public TipoUsuario getUsuarioType(String login){
        return findByLogin(login).getTipoUsuario();
    }
}
