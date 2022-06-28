package com.br.pipoca.util;

public enum Pagamento {

    DINHEIRO(0), PIX(1), CARTAO(2);

    private int valor;

    Pagamento(int valor){
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
