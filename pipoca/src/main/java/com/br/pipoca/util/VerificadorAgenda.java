package com.br.pipoca.util;

import com.br.pipoca.entity.Atendimento;
import com.br.pipoca.service.AtendimentoService;
import com.br.pipoca.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component @EnableScheduling
public class VerificadorAgenda {

    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO*60;
    private final long HORA = MINUTO*60;

    @Autowired
    private AtendimentoService atendimentoService;
    @Autowired
    HorarioService horarioService;

    @Scheduled(fixedDelay = (MINUTO/2))
    private void verificarAgenda() throws IOException {
        System.out.println("opa");
        java.util.Date date = new java.util.Date();
        List<Atendimento> atendimentos = atendimentoService.agendamentosDate(DateConverter.dateConverter(date));
        if (!(atendimentos.isEmpty())){
            for (Atendimento a:
                    atendimentos) {
                java.util.Date dateAtendimento = new java.util.Date();
                if (a.getHorario().getHora().ordinal() < Hora.values().length-1){
                    dateAtendimento.setHours(Hora.getMatrix().get(a.getHorario().getHora().ordinal()+1)[0]);
                    dateAtendimento.setMinutes(Hora.getMatrix().get(a.getHorario().getHora().ordinal()+1)[1]);
                }else {
                    dateAtendimento.setHours(19);
                    dateAtendimento.setMinutes(0);
                }

                if (dateAtendimento.after(date)
                    && a.getHorario().getStatusHorario() == StatusHorario.OCIOSO){
                    horarioService.alterarStatus(a.getHorario(), StatusHorario.ATRASADO.getValor());
                }
                System.out.println(dateAtendimento.after(date));
            }
        }
    }
}
