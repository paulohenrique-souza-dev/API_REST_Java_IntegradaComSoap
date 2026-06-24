package com.soap.apirest.controller;

import com.soap.apirest.dto.CategoriaDTO;
import com.soap.apirest.dto.EstoqueDTO;
import com.soap.apirest.dto.PedidosDTO;
import com.soap.apirest.service.EstoqueXmlParser;
import com.soap.apirest.soap.SoapClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    private final SoapClient soapClient;
    private final EstoqueXmlParser estoqueXmlParser;

    public EstoqueController(
            SoapClient soapClient,
            EstoqueXmlParser estoqueXmlParser
    ){
        this.soapClient = soapClient;
        this.estoqueXmlParser = estoqueXmlParser;
    }

    @GetMapping
    public List<EstoqueDTO> listarEstoque(){
        String xml=soapClient.listarEstoque();
        return  estoqueXmlParser.converterListaEstoque(xml);
    }

    @GetMapping("/{id}")
    public EstoqueDTO buscarEstoquePorId(@PathVariable Integer id) {
        String xml = soapClient.buscarEstoquePorId(id);
        return estoqueXmlParser.converterestoqueUnico(xml);
    }

    @PutMapping(value = "/{id}", produces = "application/xml")
    public String atualizarEstoque(
            @PathVariable Integer id,
            @RequestBody EstoqueDTO estoque
    ) {
        return soapClient.atualizarEstoque(id, estoque);
    }

    @DeleteMapping(value = "/{id}", produces = "application/xml")
    public String deletarEstoque(@PathVariable Integer id) {
        return soapClient.deletarEstoque(id);
    }






}
