package com.br.pipoca.service;

import com.br.pipoca.entity.Horario;
import com.br.pipoca.util.Hora;
import com.br.pipoca.util.StatusHorario;
import com.br.pipoca.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private FuncionarioService funcionarioService;

    public Horario saveHorario(Horario horario){
        return horarioRepository.save(horario);}

    //Buscas
    public Horario buscarPorId(long id){return horarioRepository.findById(id);}
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

    public List<Horario> buscarPorData(Date date){
        return horarioRepository.findByData(date);
    }

    public List<Horario> buscarPorFuncionarioEData(long funcionario_id, Date date){
        return horarioRepository.findByFuncionarioAndDia(date, funcionario_id);
    }

    public List<Horario> buscarPorDataEHora(Hora hora, Date date){
        return horarioRepository.findByDataAndHora(date, hora);
    }


    // ------------ //
    public void deletarHorario(Horario horario){horarioRepository.delete(horario);}

    public void deleteById(long id){horarioRepository.deleteById(id);}

    //==================================================================================

    // Alterar Status
    public Horario fecharHorario(Horario horario){
        horario.setStatusHorario(StatusHorario.FECHADO);
        return saveHorario(horario);
    }

    public Horario concluirHorario(Horario horario){
        horario.setStatusHorario(StatusHorario.CONCLUIDO);
        return saveHorario(horario);
    }

    public Horario abrirHorario(Horario horario){
        horario.setStatusHorario(StatusHorario.DISPONIVEL);
        return saveHorario(horario);
    }

    public Horario ocuparHorario(Horario horario){
        horario.setStatusHorario(StatusHorario.OCIOSO);
        return saveHorario(horario);
    }

    public Horario alterarStatus(Horario horario, int status){
        switch (status){
            case 0:
                horario.setStatusHorario(StatusHorario.CONCLUIDO);
                return saveHorario(horario);
            case 1:
                horario.setStatusHorario(StatusHorario.OCIOSO);
                return saveHorario(horario);
            case 2:
                horario.setStatusHorario(StatusHorario.FECHADO);
                return saveHorario(horario);
            case 3:
                horario.setStatusHorario(StatusHorario.CANCELADO);
                return saveHorario(horario);
            case 4:
                horario.setStatusHorario(StatusHorario.DISPONIVEL);
                return saveHorario(horario);
        }
        return horario;
    }

    //==================================================================================

    //validações
    public boolean isDisponivel(Horario horario){
        if (!(blank(horarioRepository.findByFuncionarioAndDia(horario.getDate(), horario.getFuncionario().getId())))){
            if (horarioRepository.findByHoraAndFuncionarioAndDia(horario.getFuncionario().getId(),
                    horario.getHora().ordinal(), horario.getDate()) != null){
                return false;
            }
        }
        return true;
    }

    public boolean blank(List<Horario> lista){
        if (lista.isEmpty()){
            return true;
        }
        return false;
    }

    public List<Hora> vagas(long funcionario_id, Date date){
        List<Hora> vagas = new ArrayList<Hora>();
        //System.out.println(horarioRepository.findByFuncionarioAndDia(date, funcionario_id));
        Horario novoHorario = new Horario();
        novoHorario.setDate(date);
        novoHorario.setFuncionario(funcionarioService.findById(funcionario_id));
        int dia = novoHorario.getDate().getDate();
        int mes = novoHorario.getDate().getMonth();
        int ano = novoHorario.getDate().getYear();
        for (Hora h: Hora.values()) {
            novoHorario.setHora(h);
            if (isDisponivel(novoHorario)){
                if (h.after(h.ordinal(), dia, mes, ano)){
                    vagas.add(h);
                }
            }
        }
        return vagas;
    }
}
