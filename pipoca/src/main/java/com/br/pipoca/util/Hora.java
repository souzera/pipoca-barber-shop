package com.br.pipoca.util;

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
    Hora(String valor){
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
