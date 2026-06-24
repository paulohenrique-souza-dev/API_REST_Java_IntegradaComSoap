package com.soap.apirest.dto;

import java.math.BigDecimal;

import java.util.Date;

public class EstoqueDTO {

    private Integer id_estoque;
    private BigDecimal quantidade;
    private BigDecimal estoque_minimo;
    private String data_atualizacao;
    private Integer id_produto;


    public Integer getId_estoque() {
        return id_estoque;
    }
    public void setId_estoque(Integer id_estoque) {
        this.id_estoque=id_estoque;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade=quantidade;
    }
    public BigDecimal getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(BigDecimal estoque_minimo) {
        this.estoque_minimo=estoque_minimo;
    }
    public String getData_atualizacao() {
        return data_atualizacao;
    }
    public void setData_atualizacao(String data_atualizacao) {
        this.data_atualizacao=data_atualizacao;
    }
    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto=id_produto;
    }

}
