package com.soap.apirest.controller;

import com.soap.apirest.dto.ClienteDTO;
import com.soap.apirest.service.ClienteXmlParser;
import com.soap.apirest.soap.SoapClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final SoapClient soapClient;
    private final ClienteXmlParser clienteXmlParser;

    public ClienteController(
            SoapClient soapClient,
            ClienteXmlParser clienteXmlParser
    ) {
        this.soapClient = soapClient;
        this.clienteXmlParser = clienteXmlParser;
    }

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        String xml = soapClient.listarClientes();
        return clienteXmlParser.converterListaClientes(xml);
    }

    @GetMapping(value = "/xml", produces = "application/xml")
    public String listarClientesXml() {
        return soapClient.listarClientes();
    }

    @GetMapping( "/{id}")
    public ClienteDTO buscarClientePorId(@PathVariable Integer id) {
        String xml = soapClient.buscarClientePorId(id);
        return clienteXmlParser.converterClienteUnico(xml);
    }

    @PostMapping(produces = "application/xml")
    public String criarCliente(@RequestBody ClienteDTO cliente) {
        return soapClient.criarCliente(cliente);
    }

    @PutMapping(value = "/{id}", produces = "application/xml")
    public String atualizarCliente(
            @PathVariable Integer id,
            @RequestBody ClienteDTO cliente
    ) {
        return soapClient.atualizarCliente(id, cliente);
    }

    @DeleteMapping(value = "/{id}", produces = "application/xml")
    public String deletarCliente(@PathVariable Integer id) {
        return soapClient.deletarCliente(id);
    }
}