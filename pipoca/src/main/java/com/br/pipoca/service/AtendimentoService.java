package com.br.pipoca.service;

import com.br.pipoca.entity.Atendimento;
import com.br.pipoca.entity.Horario;
import com.br.pipoca.util.Hora;
import com.br.pipoca.util.StatusHorario;
import com.br.pipoca.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private HorarioService horarioService;
    
    private Hora[] horas = Hora.values();

    public Atendimento agendar(Atendimento atendimento){
        return atendimentoRepository.save(atendimento);
    }

    public List<Atendimento> agendamentos() throws IOException {
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        return Streamable.of(atendimentoIterable).toList();
    }

    public boolean verificarHorario(Horario horario){
        return horarioService.buscarPorStatus(StatusHorario.DISPONIVEL).contains(horario);
    }

    public List<Atendimento> buscarPorData(Date data) throws IOException {
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findByData(data);
        return Streamable.of(atendimentoIterable).toList();
    }

    public List<Hora> vagas(Date data) throws IOException {
        List<Hora> vagas = Arrays.asList(horas);
        List<Atendimento> atendimentos = buscarPorData(data);
        for (Atendimento atendimento:
             atendimentos) {
            vagas.remove(atendimento.getHorario().getHora());
        }
        return vagas;
    }

    public Atendimento buscarPorId(long id) {
        return atendimentoRepository.findById(id);
    }

    public void deleteById(long id) {
        atendimentoRepository.deleteById(id);
    }

    public boolean verificarAgenda(Atendimento atendimento, Atendimento
                                   atendimentoBase) throws IOException {
        if (atendimento.getHorario().getHora() == atendimentoBase.getHorario().getHora()
                &&
            atendimento.getHorario().getFuncionario() == atendimentoBase.getHorario().getFuncionario()){
            return false;}
        return true;
    }
}
