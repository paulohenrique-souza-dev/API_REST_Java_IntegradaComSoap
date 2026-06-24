package com.soap.apirest.service;

import com.soap.apirest.dto.ItensPedidoDTO;
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
public class ItensPedidoXmlParser {

    public static List<ItensPedidoDTO> converterListaPedidos(String xml) {
        List<ItensPedidoDTO> listaPedidos = new ArrayList<>();

        try {
            DocumentBuilderFactory FactoryPedidos = DocumentBuilderFactory.newInstance();
            FactoryPedidos.setNamespaceAware(true);


            Document document = FactoryPedidos
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));


            NodeList lista = document.getElementsByTagNameNS("*", "ItemPedido");
            System.out.println("TOTAL Itens Pedido ENCONTRADOS NO XML: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++) {
                Element pedidosElement = (Element) lista.item(i);

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

                ItensPedidoDTO pedidos = new ItensPedidoDTO();
                pedidos.setIdItem(Integer.parseInt(pegarValor(pedidosElement, "id_item")));
                pedidos.setQuantidade(new BigDecimal(pegarValor(pedidosElement, "quantidade")));
                pedidos.setValorUnitario( new BigDecimal(pegarValor(pedidosElement, "valor_unitario")));
                pedidos.setIdPedido(Integer.parseInt(pegarValor(pedidosElement, "id_pedido")));
                pedidos.setIdproduto(Integer.parseInt(pegarValor(pedidosElement, "id_produto")));


                listaPedidos.add(pedidos);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter XML de iTENS Pedido: " + e.getMessage());
        }

        return listaPedidos;
    }


    public ItensPedidoDTO converterItensPedidoUnico(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document doc = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            Element root = (Element) doc.getElementsByTagNameNS("*", "GetItemPedidoByIdResult").item(0);

            if (root == null) return null;

            ItensPedidoDTO pedidosUnico = new ItensPedidoDTO();
            pedidosUnico.setIdItem(Integer.parseInt(get(root, "id_item")));
            pedidosUnico.setQuantidade(new BigDecimal(get(root, "quantidade")));
            pedidosUnico.setValorUnitario(new BigDecimal(get(root, "valor_unitario")));
            pedidosUnico.setIdPedido(new Integer(get(root, "id_pedido")));
            pedidosUnico.setIdproduto(new Integer(get(root, "id_produto")));

            return pedidosUnico;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Itens Pedido: " + e.getMessage());
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


