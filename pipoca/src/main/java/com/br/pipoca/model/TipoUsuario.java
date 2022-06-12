package com.br.pipoca.model;

public enum TipoUsuario {

    ADM(0),
    SUPER(1),
    GERENTE(2),
    FUNCIONARIO(3),
    CLIENTE(4),
    DEV(5);

    private int valor;

    TipoUsuario(int valor){this.valor = valor;}

    public int getValor(){return this.valor;}

}
