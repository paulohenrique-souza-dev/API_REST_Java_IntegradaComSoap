package com.soap.apirest.service;

import com.soap.apirest.dto.CategoriaDTO;
import com.soap.apirest.dto.ClienteDTO;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteXmlParser {

    public List<ClienteDTO> converterListaClientes(String xml) {
        List<ClienteDTO> clientes = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document document = factory
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            NodeList lista = document.getElementsByTagNameNS("*", "Cliente");

            System.out.println("TOTAL CLIENTES ENCONTRADOS NO XML: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++) {
                Element clienteElement = (Element) lista.item(i);

                ClienteDTO cliente = new ClienteDTO();
                cliente.setIdCliente(Integer.parseInt(pegarValor(clienteElement, "id_cliente")));
                cliente.setNome(pegarValor(clienteElement, "nome"));
                cliente.setCidade(pegarValor(clienteElement, "cidade"));
                cliente.setDataCadastro(pegarValor(clienteElement, "data_cadastro"));
                cliente.setTipoCliente(pegarValor(clienteElement, "tipo_cliente"));

                clientes.add(cliente);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter XML de clientes: " + e.getMessage());
        }

        return clientes;
    }

    public ClienteDTO converterClienteUnico(String xml) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document doc = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            Element root = (Element) doc
                    .getElementsByTagNameNS("*", "GetClienteByIdResult")
                    .item(0);

            if (root == null) return null;

            ClienteDTO cliente = new ClienteDTO();

            cliente.setCidade(pegarValor(root, "cidade"));
            cliente.setDataCadastro(pegarValor(root, "data_cadastro"));
            cliente.setIdCliente(converterInteger(pegarValor(root, "id_cliente")));
            cliente.setNome(pegarValor(root, "nome"));
            cliente.setTipoCliente(pegarValor(root, "tipo_cliente"));

            return cliente;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter cliente único: " + e.getMessage(), e);
        }
    }

    private Integer converterInteger(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        return Integer.parseInt(valor);
    }

    private String pegarValor(Element elementoPai, String nomeTag) {
        NodeList lista = elementoPai.getElementsByTagNameNS("*", nomeTag);

        if (lista.getLength() == 0) {
            return null;
        }

        return lista.item(0).getTextContent();
    }
}