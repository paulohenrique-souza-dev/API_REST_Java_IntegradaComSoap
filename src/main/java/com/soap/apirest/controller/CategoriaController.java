package com.soap.apirest.controller;

import com.soap.apirest.dto.CategoriaDTO;
import com.soap.apirest.dto.ClienteDTO;
import com.soap.apirest.dto.ProdutoDTO;
import com.soap.apirest.service.CategoriaXmlParser;
import com.soap.apirest.soap.SoapClient;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final SoapClient soapClient;
    private final CategoriaXmlParser categoriaXmlParser;

    public CategoriaController(SoapClient soapClient, CategoriaXmlParser categoriaXmlParser){
        this.soapClient = soapClient;
        this.categoriaXmlParser = categoriaXmlParser;
    }

    @GetMapping
    public List<CategoriaDTO> getCategorias(){
        String xml=soapClient.listarCategorias();
        return categoriaXmlParser.converterCategoriaXML(xml);
    }
    @GetMapping(value= "/xml", produces = "application/xml")
    public String ListarCategoriasXml(){
        return soapClient.listarCategorias();
    }
    @GetMapping("/{id}")
    public CategoriaDTO buscarcategoriaporid(@PathVariable Integer id) {
        String xml = soapClient.listarCategoriasPorId(id);
        return categoriaXmlParser.converterCategoriaUnico(xml);
    }

    @PostMapping(produces = "application/xml")
    public String criarCategoria(@RequestBody CategoriaDTO categoria) {
        return soapClient.criarCategoria(categoria);
    }

    @PutMapping(value = "/{id}", produces = "application/xml")
    public String atualizarCategoria(
            @PathVariable Integer id,
            @RequestBody CategoriaDTO categoria
    ) {
        return soapClient.atualizarCategoria(id, categoria);
    }

    @DeleteMapping(value = "/{id}", produces = "application/xml")
    public String deletarCategoria(@PathVariable Integer id) {
        return soapClient.deletarCategoria(id);
    }


}
