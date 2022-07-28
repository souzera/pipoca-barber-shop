package com.br.pipoca.service;

import com.br.pipoca.entity.Atendimento;
import com.br.pipoca.entity.Cliente;
import com.br.pipoca.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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

    //deletar

    public void deleteById(long id) {
        clienteRepository.deleteById(id);
    }

    //========================================================================================

    public List<Cliente> clientes() throws IOException {
        Iterable<Cliente> clienteIterable = this.clienteRepository.findAll();
        return Streamable.of(clienteIterable).toList();
    }

    public Cliente buscarPorLogin(String login){
        return clienteRepository.findByUsuario(usuarioService.findByLogin(login));
    }

    public Cliente buscarPorID(long id){
        return clienteRepository.findById(id);
    }

    public Date getAniversario(Cliente cliente){
        Date aniversario = cliente.getDtNascimento();
        aniversario.setYear(new java.util.Date().getYear());
        return aniversario;
    }

    public List<Cliente> pesquisaPorNome(String nome){
        Iterable<Cliente> clienteIterable = this.clienteRepository.findAll();
        List<Cliente> lista = new ArrayList<>();
        for (Cliente c:
             clienteIterable) {
            if (c.getNome().contains(nome)){
                lista.add(c);
            }
        }
        return lista;
    }


}
