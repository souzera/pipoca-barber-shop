package com.br.pipoca.util;

public enum StatusHorario {

    DISPONIVEL(0),
    OCUPADO(1),
    CONCLUIDO(2),

    CANCELADO(3);

    private int valor;
    StatusHorario(int valor){this.valor=valor;}

    public int getValor() {
        return valor;
    }
}
