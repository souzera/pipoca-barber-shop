package com.br.pipoca.util;

import javax.swing.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Semana {

    private static long DIA = 86400000;
    public static void getSemana(Date date) {
        List<Date> semana = new ArrayList<Date>();
        anteriores(semana, date);
        semana.add(date);
        posteriores(semana, date);
        System.out.println(semana);
    }

    private static void posteriores(List<Date> array, Date date){
        java.util.Date prox = DateConverter.toJavaDate(date);
        int limiteInit = prox.getDay();
        while (prox.getDay() < 6){
            if(prox.getDay() == 0){
                System.out.println("parei");
                break;}
            prox.setTime(prox.getTime()+DIA);
            array.add(DateConverter.dateConverter(prox));
        }
    }

    private static void anteriores(List<Date> array, Date date){
        java.util.Date ant = DateConverter.toJavaDate(date);
        int limiteInit = ant.getDay();
        while (ant.getDay() > 0){
            if (ant.getDay()==6){
                break;}
            ant.setTime(ant.getTime()-DIA);
            array.add(0,DateConverter.dateConverter(ant));
        }

    }
}
