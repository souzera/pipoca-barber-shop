package com.br.pipoca.dto;

import com.br.pipoca.entity.Produto;

public class ProdutoDTO {

    private String descricao;
    private String categoria;
    private String marca;
    private float valor;

    public ProdutoDTO() {}

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Produto toProduto(){
        Produto produto = new Produto();
        produto.setDescricao(this.descricao);
        produto.setCategoria(this.categoria);
        produto.setMarca(this.marca);
        produto.setValor(this.valor);
        return produto;
    }
}
