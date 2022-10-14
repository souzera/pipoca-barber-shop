package com.br.pipoca.service;

import com.br.pipoca.entity.Atendimento;
import com.br.pipoca.entity.Funcionario;
import com.br.pipoca.repository.AtendimentoRepository;
import com.br.pipoca.util.GeradorPdf;
import com.br.pipoca.util.StatusHorario;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;
    @Autowired
    private FuncionarioService funcionarioService;

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

    public List<Atendimento> agendamentosFuncionario(long funcionario_id) throws IOException {
        Funcionario f = funcionarioService.findById(funcionario_id);
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a:
             atendimentoIterable) {
            if (a.getHorario().getFuncionario() == f){
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
        Iterable<Atendimento> atendimentoIterable = agendamentosDate(date);
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a:
                atendimentoIterable) {
            if (a.getHorario().getFuncionario() == funcionario){
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
            || a.getHorario().getStatusHorario() == StatusHorario.CONCLUIDO){
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

    public List<Atendimento> ociososFuncionario(long funcionario_id){
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a: atendimentoIterable) {
            if (a.getHorario().getFuncionario().getId() == funcionario_id &&
                    a.getHorario().getStatusHorario() == StatusHorario.OCIOSO){
                lista.add(a);
            }
        }
        return lista;
    }

    public List<Atendimento> concluidosFuncionario(long funcionario_id){
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a: atendimentoIterable) {
            if (a.getHorario().getFuncionario().getId() == funcionario_id &&
                    a.getHorario().getStatusHorario() == StatusHorario.CONCLUIDO){
                lista.add(a);
            }
        }
        return lista;
    }

    public List<Atendimento> listarPorStatus(StatusHorario statusHorario){
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a: atendimentoIterable) {
            if (a.getHorario().getStatusHorario() == statusHorario){
                lista.add(a);
            }
        }
        return lista;
    }

    public List<Atendimento> listarPorStatusEFuncionario(long funcionario_id, StatusHorario statusHorario) throws IOException {
        Iterable<Atendimento> atendimentoIterable = agendamentosFuncionario(funcionario_id);
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a: atendimentoIterable) {
            if (a.getHorario().getStatusHorario() == statusHorario){
                lista.add(a);
            }
        }
        return lista;
    }

    public List<Atendimento> listarPorClienteEStatus(long cliente_id, StatusHorario status){
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findByCliente(cliente_id);
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a: atendimentoIterable) {
            if (a.getHorario().getStatusHorario() == status){
                lista.add(a);
            }
        }
        return lista;
    }

    public List<Atendimento> listarPorDiaFuncionario(long funcionario_id, Date date){
        Iterable<Atendimento> atendimentoIterable = this.atendimentoRepository.findAll();
        List<Atendimento> lista = new ArrayList<>();
        for (Atendimento a: atendimentoIterable) {
            if (a.getHorario().getFuncionario().getId() == funcionario_id &&
                    a.getHorario().getDate() == date){
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

    public float progressoDiario(Date date) throws IOException {
        List<Atendimento> atendimentos = agendamentosDate(date);
        List<Atendimento> ociososDate = new ArrayList<>();
        for (Atendimento a:atendimentos) {
            if (a.getHorario().getStatusHorario()==StatusHorario.OCIOSO){
                ociososDate.add(a);
            }
        }
        if (ociososDate.size() == 0){return 100;}
        float progresso = (ociososDate.size()*100)/atendimentos.size();
        return 100-progresso;
    }

    public float progressoFuncionario(long funcionario_id) throws IOException {
        List<Atendimento> atendimentos = agendamentosFuncionario(funcionario_id);
        List<Atendimento> ociososFuncionario = ociososFuncionario(funcionario_id);
        if (ociososFuncionario.size()>0){
            float progresso = (ociososFuncionario.size()*100)/atendimentos.size();
            return 100-progresso;
        }
        return 0;
    }

    public void gerarRelatorioDay(Date date) throws IOException, DocumentException {
        try {
            GeradorPdf.relatorioAtendimentos(agendamentosDate(date));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
