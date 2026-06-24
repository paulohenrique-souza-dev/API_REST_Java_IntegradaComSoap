package com.soap.apirest.controller;

import com.soap.apirest.dto.FornecedoresDTO;
import com.soap.apirest.dto.PedidosDTO;
import com.soap.apirest.service.PedidosXmlParser;
import com.soap.apirest.soap.SoapClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {


    private final SoapClient soapClient;
    private final PedidosXmlParser pedidosXmlParser;

    public PedidosController(SoapClient soapClient, PedidosXmlParser pedidosXmlParser) {
        this.soapClient = soapClient;
        this.pedidosXmlParser = pedidosXmlParser;
    }

    @GetMapping
    public List<PedidosDTO> getPedidos() {
        String xml = soapClient.listarPedidos();
        return PedidosXmlParser.converterListaPedidos(xml);
    }
    @GetMapping("/{id}")
    public PedidosDTO buscarPedidosPorId(@PathVariable Integer id) {
        String xml = soapClient.buscarPedidosPorId(id);
        return pedidosXmlParser.converterPedidosUnico(xml);
    }
    @PostMapping(produces = "application/xml")
    public String criarPedido(@RequestBody PedidosDTO pedido) {
        return soapClient.criarPedido(pedido);
    }

    @PutMapping(value = "/{id}", produces = "application/xml")
    public String atualizarPedido(
            @PathVariable Integer id,
            @RequestBody PedidosDTO pedido
    ) {
        return soapClient.atualizarPedido(id, pedido);
    }

    @DeleteMapping(value = "/{id}", produces = "application/xml")
    public String deletarPedido(@PathVariable Integer id) {
        return soapClient.deletarPedido(id);
    }

}