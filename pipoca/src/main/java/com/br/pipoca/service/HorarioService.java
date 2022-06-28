package com.br.pipoca.service;

import com.br.pipoca.entity.Horario;
import com.br.pipoca.util.Hora;
import com.br.pipoca.util.StatusHorario;
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

    //private static int[][] matrix = {{8,0},{8,30},{9,0},{9,30},{10,0},{10,30},{11,0},{11,30},
    // {14,0},{14,30},{15,0},{15,30},{16,0},{16,30},{17,0},{17,30},{18,0},{18,30}};

    public Horario saveHorario(Horario horario){
        horario.setStatusHorario(StatusHorario.DISPONIVEL);
        return horarioRepository.save(horario);}
    public Horario buscarPorId(long id){return horarioRepository.findById(id);}
    public void setHora(Horario horario, int hora){}
    public List<Horario> horarios() throws IOException {
        Iterable<Horario> horarioIterable = this.horarioRepository.findAll();
        return Streamable.of(horarioIterable).toList();
    }

    public List<Horario> buscarPorStatus(StatusHorario status){
        Iterable<Horario> horarioIterable = horarioRepository.findStatus(status.getValor());
        return Streamable.of(horarioIterable).toList();
    }

    public List<Horario> horarioFuncionario(long id){
        Iterable<Horario> horarioIterable = this.horarioRepository.findFuncionario(id);
        return Streamable.of(horarioIterable).toList();
    }

    public Horario buscarPorHoraEFuncionario(long funcionario_id, Hora hora){
        return horarioRepository.findByHoraAndFuncionario(funcionario_id,hora);
    }

    public void deletarHorario(Horario horario){
        horarioRepository.delete(horario);
    }
}
