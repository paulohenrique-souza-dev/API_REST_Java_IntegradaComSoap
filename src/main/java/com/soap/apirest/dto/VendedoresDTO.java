package com.soap.apirest.dto;

import java.math.BigDecimal;

import java.util.Date;

public class VendedoresDTO {

    Integer idVendedor;
    String nomeVendedor;
    String regiao;
    BigDecimal salario;
    String dataAdmissao;


    public Integer getIdVendedor() {
        return  idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNomeVendedor(){
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor){
        this.nomeVendedor = nomeVendedor;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setregiao(String regiao) {
        this.regiao = regiao;

    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;


    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }


}























































































































