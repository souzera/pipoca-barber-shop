package com.br.pipoca.util;

public enum StatusHorario {

    CONCLUIDO(0),
    OCIOSO(1),
    ATRASADO(2),
    CANCELADO(3),
    DISPONIVEL(4);

    private int valor;
    StatusHorario(int valor){this.valor=valor;}

    public int getValor() {
        return valor;
    }
}
