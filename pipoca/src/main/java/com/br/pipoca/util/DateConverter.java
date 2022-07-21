package com.br.pipoca.util;

import java.sql.Date;

public class DateConverter {

    public static Date dateConverter(java.util.Date date){
        int ano = date.getYear();
        int mes = date.getMonth();
        int dia = date.getDate();
        return new Date(ano,mes,dia);
    }
}
