package com.br.pipoca.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateConverter {

    public static Date dateConverter(java.util.Date date){
        int ano = date.getYear();
        int mes = date.getMonth();
        int dia = date.getDate();
        return new Date(ano,mes,dia);
    }

    public static java.util.Date toJavaDate(Date date){
        java.util.Date javaDate = new java.util.Date();
        javaDate.setDate(date.getDate());
        javaDate.setMonth(date.getMonth());
        javaDate.setYear(date.getYear());

        return javaDate;
    }

    public static String textDateFormat(java.util.Date date){
        SimpleDateFormat patternDate = new SimpleDateFormat("dd/MM/yyyy");
        return patternDate.format(date);
    }
}
