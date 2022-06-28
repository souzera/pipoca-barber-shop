package com.br.pipoca.util;

public enum Cargo {

    ADM(0), BARBEIRO(1), ATENDENTE(2);

    private int valor;
    Cargo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
