package com.br.pipoca.service;

import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Horario;
import com.br.pipoca.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private FuncionarioService funcionarioService;

    public Horario saveHorario(Horario horario){
        return horarioRepository.save(horario);
    }

    public List<Horario> horarios() throws IOException {
        Iterable<Horario> horarioIterable = this.horarioRepository.findAll();
        return Streamable.of(horarioIterable).toList();
    }

    public void deletarHorario(long id){}

    public List<Horario> horarioFuncionario(long id){
        Iterable<Horario> horarioIterable = this.horarioRepository.findFuncionario(id);
        return Streamable.of(horarioIterable).toList();
    }
}
