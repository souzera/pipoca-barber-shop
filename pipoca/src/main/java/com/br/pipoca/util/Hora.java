package com.br.pipoca.util;

public enum Hora {

    _8H00("08h00"),
    _8H30("08h30"),
    _9H00("09h00"),
    _9H30("09h30"),
    _10H00("10h00"),
    _10H30("10h30"),
    _11H00("11h00"),
    _11H30("11h30"),
    _14H00("14h00"),
    _14H30("14h30"),
    _15H00("15h00"),
    _15H30("15h30"),
    _16H00("16h00"),
    _16H30("16h30"),
    _17H00("17h00"),
    _17H30("17h30"),
    _18H00("18h00"),
    _18H30("18h30");

    private String valor;
    Hora(String valor){
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
