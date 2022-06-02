package com.br.pipoca.model;

public enum Sexo {

    MASCULINO("M"),FEMININO("F");

    private String valor;

    Sexo(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return this.valor;
    }


}
