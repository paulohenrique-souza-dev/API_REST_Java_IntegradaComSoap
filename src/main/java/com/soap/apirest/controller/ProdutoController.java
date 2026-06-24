package com.soap.apirest.controller;

import com.soap.apirest.dto.ProdutoDTO;
import com.soap.apirest.service.ProdutoXmlParser;
import com.soap.apirest.soap.SoapClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {


    private final SoapClient soapClient;
    private final ProdutoXmlParser produtoXmlParser;

    public ProdutoController(SoapClient soapClient, ProdutoXmlParser produtoXmlParser) {
        this.soapClient = soapClient;
        this.produtoXmlParser = produtoXmlParser;
    }
    @GetMapping
    public List<ProdutoDTO> getProdutos() {
        String xml=soapClient.listarProdutos();
        return produtoXmlParser.converterListaProduto(xml);
    }

    @GetMapping(value = "/xml", produces ="application/xml")
    public String ListarProdutosXml() {
        return soapClient.listarProdutos();
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscarProdutoPorId(@PathVariable Integer id) {
        String xml = soapClient.buscarProdutoPorId(id);
        return produtoXmlParser.converterProdutoUnico(xml);
    }


    @PostMapping(produces = "application/xml")
    public String criarProduto(@RequestBody ProdutoDTO produto) {
        return soapClient.criarProduto(produto);
    }

    @PutMapping(value = "/{id}", produces = "application/xml")
    public String atualizarProduto(
            @PathVariable Integer id,
            @RequestBody ProdutoDTO produto
    ) {
        return soapClient.atualizarProduto(id, produto);
    }

    @DeleteMapping(value = "/{id}", produces = "application/xml")
    public String deletarProduto(@PathVariable Integer id) {
        return soapClient.deletarProduto(id);
    }
}
