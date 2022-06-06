package com.br.pipoca.model;

public enum Sexo {

    //MASCULINO("M"),FEMININO("F");
    MASCULINO(0),FEMININO(1);

    private int valor;

    Sexo(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return this.valor;
    }

}
