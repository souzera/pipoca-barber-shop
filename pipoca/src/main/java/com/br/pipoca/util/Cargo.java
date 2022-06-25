package com.br.pipoca.util;

public enum Cargo {

    BARBEIRO(1), ATENDENTE(2), ADM(0);

    private int valor;
    Cargo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
