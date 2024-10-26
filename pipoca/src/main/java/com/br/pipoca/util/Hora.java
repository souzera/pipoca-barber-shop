package com.br.pipoca.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public enum Hora {

    _7H00("07h00"),//0
    _7H30("07h30"),//1
    _8H00("08h00"),//2
    _8H30("08h30"),//3
    _9H00("09h00"),//4
    _9H30("09h30"),//5
    _10H00("10h00"),//6
    _10H30("10h30"),//7
    _11H00("11h00"),//8
    _11H30("11h30"),//9
    _14H00("14h00"),//10
    _14H30("14h30"),//11
    _15H00("15h00"),//12
    _15H30("15h30"),//13
    _16H00("16h00"),//14
    _16H30("16h30"),//15
    _17H00("17h00"),//16
    _17H30("17h30"),//17
    _18H00("18h00"),//18
    _18H30("18h30"),//19
    _19H00("19h00"),//20
    _19H30("19h30"),//21
    _20H00("20h00"),//22
    _20H30("20h30"),//23
    _21H00("21h00"),//24
    _21H30("21h30"),//25
    _22H00("22h00");//26
    private String valor;
    private Date agora;
    private static int[][] matrix = {{7,0},{7,30},{8,0},{8,30},{9,0},{9,30},{10,0},{10,30},{11,0},{11,30},
            {14,0},{14,30},{15,0},{15,30},{16,0},{16,30},{17,0},{17,30},{18,0},{18,30},{19, 0},{19, 30}, {20,0}, {20, 30}, {21, 0}, {21, 30}, {22, 0}};
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

    public static List<int[]> getMatrix() {
        List<int[]> lista = Arrays.asList(matrix);
        return lista;
    }
}
