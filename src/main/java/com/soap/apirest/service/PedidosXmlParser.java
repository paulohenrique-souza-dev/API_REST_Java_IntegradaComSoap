package com.soap.apirest.service;

import com.soap.apirest.dto.PedidosDTO;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

@Service
public class PedidosXmlParser {

    public static List<PedidosDTO> converterListaPedidos(String xml) {
        List<PedidosDTO> listaPedidos = new ArrayList<>();

        try {
            DocumentBuilderFactory FactoryPedidos = DocumentBuilderFactory.newInstance();
            FactoryPedidos.setNamespaceAware(true);


            Document document = FactoryPedidos
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));


            NodeList lista = document.getElementsByTagNameNS("*", "Pedido");
            System.out.println("TOTAL Pedidos ENCONTRADOS NO XML: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++) {
                Element pedidosElement = (Element) lista.item(i);

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

                PedidosDTO pedidos = new PedidosDTO();
                pedidos.setIdPedido(Integer.parseInt(pegarValor(pedidosElement, "id_pedido")));
                pedidos.setDataPedido(pegarValor(pedidosElement, "data_pedido"));
                pedidos.setStatusPedido(pegarValor(pedidosElement, "status_pedido"));
                pedidos.setIdCliente(Integer.parseInt(pegarValor(pedidosElement, "id_cliente")));
                pedidos.setIdVendedor(Integer.parseInt(pegarValor(pedidosElement, "id_vendedor")));


                listaPedidos.add(pedidos);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter XML de Pedidos: " + e.getMessage());
        }

        return listaPedidos;
    }


    public PedidosDTO converterPedidosUnico(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document doc = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            Element root = (Element) doc.getElementsByTagNameNS("*", "GetPedidoByIdResult").item(0);

            if (root == null) return null;

            PedidosDTO pedidosUnico = new PedidosDTO();
            pedidosUnico.setIdPedido(Integer.parseInt(get(root, "id_pedido")));
            pedidosUnico.setDataPedido(get(root, "data_pedido"));
            pedidosUnico.setStatusPedido(get(root, "status_pedido"));
            pedidosUnico.setIdCliente(new Integer(get(root, "id_cliente")));
            pedidosUnico.setIdVendedor(new Integer(get(root, "id_vendedor")));

            return pedidosUnico;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Pedidos: " + e.getMessage());
        }
    }

    private String get(Element el, String tag) {
        NodeList list = el.getElementsByTagNameNS("*", tag);
        return list.getLength() > 0 ? list.item(0).getTextContent() : null;
    }

    private static String pegarValor(Element elementoPai, String nomeTag) {
        NodeList listaPedidos = elementoPai.getElementsByTagNameNS("*", nomeTag);

        if (listaPedidos.getLength() == 0) {
            return null;
        }

        return listaPedidos.item(0).getTextContent();
    }
}


