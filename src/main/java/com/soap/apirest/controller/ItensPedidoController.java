package com.soap.apirest.controller;

import com.soap.apirest.dto.FornecedoresDTO;
import com.soap.apirest.dto.ItensPedidoDTO;
import com.soap.apirest.service.ItensPedidoXmlParser;
import com.soap.apirest.soap.SoapClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/itenspedido")
public class ItensPedidoController {


    private final SoapClient soapClient;
    private final ItensPedidoXmlParser  itensPedidoXmlParser;

    public ItensPedidoController(SoapClient soapClient, ItensPedidoXmlParser itensPedidoXmlParser) {
        this.soapClient = soapClient;
        this.itensPedidoXmlParser = itensPedidoXmlParser;
    }

    @GetMapping
    public List<ItensPedidoDTO> getItemPedidos() {
        String xml = soapClient.listarItensPedido();
        return ItensPedidoXmlParser.converterListaPedidos(xml);
    }
    @GetMapping("/{id}")
    public ItensPedidoDTO buscarItensPedidoPorId(@PathVariable Integer id) {
        String xml = soapClient.buscarItensPedidoPorId(id);
        return itensPedidoXmlParser.converterItensPedidoUnico(xml);
    }
    @PostMapping(produces = "application/xml")
    public String criarItemPedido(@RequestBody ItensPedidoDTO item_pedido) {
        return soapClient.criarItensPedido(item_pedido);
    }

    @PutMapping(value = "/{id}", produces = "application/xml")
    public String atualizarItemPedido(
            @PathVariable Integer id,
            @RequestBody ItensPedidoDTO item_pedido
    ) {
        return soapClient.atualizarItensPedido(id, item_pedido);
    }

    @DeleteMapping(value = "/{id}", produces = "application/xml")
    public String deletarItemPedido(@PathVariable Integer id) {
        return soapClient.deletarItensPedido(id);
    }

}