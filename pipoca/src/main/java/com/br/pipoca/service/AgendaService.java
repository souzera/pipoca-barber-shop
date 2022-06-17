package com.br.pipoca.service;

import com.br.pipoca.entity.Agenda;
import com.br.pipoca.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private HorarioService horarioService;

    public Agenda agendar(Agenda agenda){
        return agendaRepository.save(agenda);
    }

    public List<Agenda> agendamentos() throws IOException {
        Iterable<Agenda> agendaIterable = this.agendaRepository.findAll();
        return Streamable.of(agendaIterable).toList();
    }

    public void verificarHorario(){

    }

}
