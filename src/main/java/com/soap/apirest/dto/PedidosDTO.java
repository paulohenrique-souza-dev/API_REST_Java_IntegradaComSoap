package com.soap.apirest.dto;

public class PedidosDTO {
    private Integer idPedido;
    private String dataPedido;
    private String statusPedido;
    private Integer idCliente;
    private Integer idVendedor;


    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido){
        this.idPedido = idPedido;
    }
    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido){
        this.dataPedido = dataPedido;
    }
    public String getStatusPedido() {
        return statusPedido;
    }
    public void setStatusPedido(String statusPedido){
        this.statusPedido = statusPedido;
    }
    public Integer getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Integer idCliente){
        this.idCliente = idCliente;
    }
    public Integer getIdVendedor() {
        return idVendedor;
    }
    public  void setIdVendedor(Integer idVendedor){
        this.idVendedor = idVendedor;
    }
}
