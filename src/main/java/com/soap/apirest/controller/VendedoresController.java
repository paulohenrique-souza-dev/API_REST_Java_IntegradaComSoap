package com.soap.apirest.controller;

import com.soap.apirest.dto.ProdutoDTO;
import com.soap.apirest.dto.VendedoresDTO;
import com.soap.apirest.service.VendedoresXmlParser;
import com.soap.apirest.soap.SoapClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/vendedores")

public class VendedoresController {
    private final SoapClient soapClient;
    private final VendedoresXmlParser vendedoresXmlParser;


    public VendedoresController(SoapClient soapClient, VendedoresXmlParser vendedoresXmlParser){
        this.soapClient = soapClient;
        this.vendedoresXmlParser = vendedoresXmlParser;
    }

    @GetMapping
    public List<VendedoresDTO> listarvendedores(){
        String xml = soapClient.listarVendedores();
        return vendedoresXmlParser.converterListaVendedores(xml);
    }


    @GetMapping(value="/xml", produces="application/xml")
    public String listarVendedores(){
        return soapClient.listarVendedores();
    }

    @PostMapping(produces = "application/xml")
    public String criarVendedor(@RequestBody VendedoresDTO vendedores) {
        return soapClient.criarVendedores(vendedores);
    }

    @GetMapping("/{id}")
    public VendedoresDTO buscarVendedorPorId(@PathVariable Integer id) {
        String xml = soapClient.buscarVendedorPorId(id);
        return vendedoresXmlParser.converterVendedorUnico(xml);
    }

    @GetMapping(value = "/{id}/xml", produces = "text/plain")
    public String buscarVendedorPorIdXml(@PathVariable Integer id) {
        String xml = soapClient.buscarVendedorPorId(id);

        System.out.println("========== XML RECEBIDO POR ID ==========");
        System.out.println(xml);
        System.out.println("========================================");

        return xml;
    }
}
