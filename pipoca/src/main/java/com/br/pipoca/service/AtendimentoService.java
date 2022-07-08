package com.br.pipoca.service;

import com.br.pipoca.entity.Atendimento;
import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.entity.Horario;
import com.br.pipoca.util.Hora;
import com.br.pipoca.util.StatusHorario;
import com.br.pipoca.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    public Atendimento agendar(Atendimento atendimento){
        return atendimentoRepository.save(atendimento);
    }

    public List<Atendimento> agendamentos() throws IOException {
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        return Streamable.of(atendimentoIterable).toList();
    }
    public Atendimento buscarPorId(long id) {
        return atendimentoRepository.findById(id);
    }

    public void deleteById(long id) {
        atendimentoRepository.deleteById(id);
    }

    public List<Atendimento> agendamentosFuncionario(Funcionario funcionario) throws IOException {
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a:
             atendimentoIterable) {
            if (a.getHorario().getFuncionario() == funcionario){
                lista.add(a);
            }
        }
        return lista;
    }

    public List<Atendimento> agendamentosDate(Date date) throws IOException {
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a:
                atendimentoIterable) {
            if (a.getHorario().getDate() == date){
                lista.add(a);
            }
        }
        return lista;
    }

    public List<Atendimento> agendamentosDateEFuncionario(Funcionario funcionario, Date date) throws IOException {
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a:
                atendimentoIterable) {
            if (a.getHorario().getDate() == date
                    &&
                a.getHorario().getFuncionario() == funcionario){
                lista.add(a);
            }
        }
        return lista;
    }

}
