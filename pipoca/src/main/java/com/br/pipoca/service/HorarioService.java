package com.br.pipoca.service;

import com.br.pipoca.entity.Horario;
import com.br.pipoca.model.GradeHorario;
import com.br.pipoca.model.StatusHorario;
import com.br.pipoca.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
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

    public List<Horario> buscarPorHora(Date date){
        Iterable<Horario> horarioIterable = horarioRepository.findHora(date);
        return Streamable.of(horarioIterable).toList();
    }

    public List<Horario> buscarPorStatus(StatusHorario status){
        Iterable<Horario> horarioIterable = horarioRepository.findStatus(status);
        return Streamable.of(horarioIterable).toList();
    }

    public List<Horario> horarioFuncionario(long id){
        Iterable<Horario> horarioIterable = this.horarioRepository.findFuncionario(id);
        return Streamable.of(horarioIterable).toList();
    }

    public void grade(){
        GradeHorario grade = new GradeHorario();
        int i = 0;
        for (int[] e: GradeHorario.getMatrix()
        ) {
            System.out.println(GradeHorario.pegarHorario(new Date(), i));
            i++;
        }
    }
}
