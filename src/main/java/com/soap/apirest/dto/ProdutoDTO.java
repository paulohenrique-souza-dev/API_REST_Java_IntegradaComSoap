package com.soap.apirest.dto;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Integer idProduto;
    private String nomeProduto;
    private Integer idCategoria;
    private BigDecimal preco;
    private BigDecimal custo;
    private Integer idFornecedor;



    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;

    }
    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco=preco;
    }
    public BigDecimal getCusto() {
        return custo;
    }
    public void setCusto(BigDecimal custo) {
        this.custo=custo;
    }
    public Integer getIdFornecedor(){
        return idFornecedor;
    }
    public Integer idFornecedor() {
        return idFornecedor;
    }
    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor=idFornecedor;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria=idCategoria;
    }
}
