package com.br.pipoca.util;

public enum StatusHorario {

    DISPONIVEL(0),
    OCUPADO(1),
    CONCLUIDO(2);

    private int valor;
    StatusHorario(int valor){this.valor=valor;}

    public int getValor() {
        return valor;
    }
}
