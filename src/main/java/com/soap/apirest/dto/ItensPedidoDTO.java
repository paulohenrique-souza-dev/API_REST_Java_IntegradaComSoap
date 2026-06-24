package com.soap.apirest.dto;

import java.math.BigDecimal;

public class ItensPedidoDTO {

    private Integer idItem;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private Integer idPedido;
    private Integer idproduto;

    public Integer getIdItem() {
        return idItem;
    }
    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }
    public BigDecimal getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public Integer getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
    public Integer getIdproduto() {
        return idproduto;
    }
    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
    }
}
