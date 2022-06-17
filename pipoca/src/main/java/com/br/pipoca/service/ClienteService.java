package com.br.pipoca.service;

import com.br.pipoca.entity.Cliente;
import com.br.pipoca.entity.Usuario;
import com.br.pipoca.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> clientes() throws IOException {
        Iterable<Cliente> clienteIterable = this.clienteRepository.findAll();
        return Streamable.of(clienteIterable).toList();
    }

    public Cliente buscarPorLogin(String login){
        return clienteRepository.findByUsuario(usuarioService.findByLogin(login));
    }

}
