package com.br.pipoca.service;

import com.br.pipoca.entity.Atendimento;
import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.repository.AtendimentoRepository;
import com.br.pipoca.util.StatusHorario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
            if (a.getHorario().getDate().equals(date)){
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

    public List<Atendimento> agendamentosCliente(long cliente_id){
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findByCliente(cliente_id);
        return Streamable.of(atendimentoIterable).toList();
    }

    public List<Atendimento> finalizados(){
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a: atendimentoIterable) {
            if (a.getHorario().getStatusHorario() == StatusHorario.CANCELADO
            || a.getHorario().getStatusHorario() == StatusHorario.CONCLUIDO
            || a.getHorario().getStatusHorario() == StatusHorario.FECHADO){
                lista.add(a);
            }
        }
        return lista;
    }

    public List<Atendimento> ociosos(){
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a: atendimentoIterable) {
            if (a.getHorario().getStatusHorario() == StatusHorario.OCIOSO){
                lista.add(a);
            }
        }
        return lista;
    }

    public Atendimento atualizarAgenda(long agenda_id, Atendimento novoAtendimento){
        Atendimento a = buscarPorId(agenda_id);
        a.setHorario(novoAtendimento.getHorario());
        a.setCliente(novoAtendimento.getCliente());
        a.setServico(novoAtendimento.getServico());
        return atendimentoRepository.save(a);
    }

}
