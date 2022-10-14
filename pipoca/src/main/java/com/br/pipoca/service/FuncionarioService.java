package com.br.pipoca.service;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.repository.FuncionarioRepository;
import com.br.pipoca.util.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Funcionario saveFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }
    public List<Funcionario> funcionarios() throws IOException {
        Iterable<Funcionario> funcionarioIterable = this.funcionarioRepository.findAll();
        return Streamable.of(funcionarioIterable).toList();
    }

    public Funcionario findById(long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario findByLogin(String login){
        Iterable<Funcionario> funcionarioIterable = this.funcionarioRepository.findAll();
        for (Funcionario f: funcionarioIterable) {
            if (f.getUsuario().getLogin() == login){
                return f;
            }
        }
        return null;
    }

    public List<Funcionario> findByCargo(Cargo cargo){return funcionarioRepository.findByCargo(cargo.getValor());}

    public void deleteById(long id) {
        funcionarioRepository.deleteById(id);
    }
}
