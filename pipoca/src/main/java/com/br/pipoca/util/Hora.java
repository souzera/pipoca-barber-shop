package com.br.pipoca.util;

import java.util.Date;

public enum Hora {

    _8H00("08h00"),//0
    _8H30("08h30"),//1
    _9H00("09h00"),//2
    _9H30("09h30"),//3
    _10H00("10h00"),//4
    _10H30("10h30"),//5
    _11H00("11h00"),//6
    _11H30("11h30"),//7
    _14H00("14h00"),//8
    _14H30("14h30"),//9
    _15H00("15h00"),//10
    _15H30("15h30"),//11
    _16H00("16h00"),//12
    _16H30("16h30"),//13
    _17H00("17h00"),//14
    _17H30("17h30"),//15
    _18H00("18h00"),//16
    _18H30("18h30");//17
    private String valor;
    private Date agora;
    private static int[][] matrix = {{8,0},{8,30},{9,0},{9,30},{10,0},{10,30},{11,0},{11,30},
            {14,0},{14,30},{15,0},{15,30},{16,0},{16,30},{17,0},{17,30},{18,0},{18,30}};
    Hora(String valor){
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public boolean after(int ordinal, int day, int month, int year){
        Date agora = new Date();
        Date horaOf = new Date();
        //
        horaOf.setYear(year);
        horaOf.setMonth(month);
        horaOf.setDate(day);
        horaOf.setHours(matrix[ordinal][0]);
        horaOf.setMinutes(matrix[ordinal][1]);
        return horaOf.after(agora);
    }
}
