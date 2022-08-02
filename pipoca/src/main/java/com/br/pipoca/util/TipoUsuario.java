package com.br.pipoca.util;

public enum TipoUsuario {

    ADM(0),
    SUPER(1),
    BARBEIRO(2),
    ATENDENTE(3),
    CLIENTE(4),
    DEV(5);

    private int valor;

    TipoUsuario(int valor){this.valor = valor;}

    public int getValor(){return this.valor;}

}
