package com.br.pipoca.service;

import com.br.pipoca.entity.Cliente;
import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario saveFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }
    public List<Funcionario> funcionarios() throws IOException {
        Iterable<Funcionario> funcionarioIterable = this.funcionarioRepository.findAll();
        return Streamable.of(funcionarioIterable).toList();
    }
}
