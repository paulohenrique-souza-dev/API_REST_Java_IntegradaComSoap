package com.soap.apirest.controller;

import com.soap.apirest.dto.CategoriaDTO;
import com.soap.apirest.dto.FornecedoresDTO;
import com.soap.apirest.service.FornecedoresXmlParser;
import com.soap.apirest.soap.SoapClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/fornecedores")
public class FornecedoresController {

    private final SoapClient soapClient;
    private final FornecedoresXmlParser fornecedoresXmlParser;

    public FornecedoresController(SoapClient soapClient, FornecedoresXmlParser fornecedoresXmlParser) {
        this.soapClient = soapClient;
        this.fornecedoresXmlParser = fornecedoresXmlParser;
    }
    @GetMapping
    public List<FornecedoresDTO> listarFornecedores() {
        String xml = soapClient.listarFornecedores();
        return fornecedoresXmlParser.converterListaFornecedores(xml);
    }
    @GetMapping(value = "/xml", produces = "application/xml")
    public String listarFornecedoresXml() {
        return soapClient.listarFornecedores();
    }
    @PostMapping(produces = "application/xml")
    public String criarFornecedor(@RequestBody FornecedoresDTO fornecedor) {
        return soapClient.criarFornecedores(fornecedor);
    }

    @PutMapping(value = "/{id}", produces = "application/xml")
    public String atualizarFornecedores(
            @PathVariable Integer id,
            @RequestBody FornecedoresDTO fornecedor
    ) {
        return soapClient.atualizarFornecedores(id, fornecedor);
    }

    @DeleteMapping(value = "/{id}", produces = "application/xml")
    public String deletarFornecedor(@PathVariable Integer id) {
        return soapClient.deletarFornecedor(id);
    }


}
