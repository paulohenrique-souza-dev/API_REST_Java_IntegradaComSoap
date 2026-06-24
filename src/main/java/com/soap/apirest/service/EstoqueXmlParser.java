package com.soap.apirest.service;

import com.soap.apirest.dto.EstoqueDTO;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class EstoqueXmlParser {

    public List<EstoqueDTO> converterListaEstoque(String xml) {
        List<EstoqueDTO> listaEstoque = new ArrayList<>();

        try {
            DocumentBuilderFactory FactoryEstoque = DocumentBuilderFactory.newInstance();
            FactoryEstoque.setNamespaceAware(true);


            Document document = FactoryEstoque
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));


            NodeList lista = document.getElementsByTagNameNS("*", "Estoque");
            System.out.println("TOTAL de ESTOQUE ENCONTRADOS NO XML: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++) {
                Element estoqueElement = (Element) lista.item(i);

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

                EstoqueDTO estoque = new EstoqueDTO();
                estoque.setId_estoque(Integer.parseInt(pegarValor(estoqueElement, "id_estoque")));
                estoque.setQuantidade(new BigDecimal(pegarValor(estoqueElement, "quantidade")));
                estoque.setEstoque_minimo(new BigDecimal(pegarValor(estoqueElement, "estoque_minimo")));
                estoque.setData_atualizacao (pegarValor(estoqueElement, "data_atualizacao"));
                estoque.setId_produto(Integer.parseInt(pegarValor(estoqueElement, "id_produto")));


                listaEstoque.add(estoque);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter XML de Estoque: " + e.getMessage());
        }

        return listaEstoque;
    }


    public EstoqueDTO converterestoqueUnico(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document doc = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            Element root = (Element) doc.getElementsByTagNameNS("*", "GetEstoqueByIdResult").item(0);

            if (root == null) return null;

            EstoqueDTO estoque = new EstoqueDTO();
            estoque.setId_estoque(Integer.parseInt(get(root, "id_estoque")));
            estoque.setQuantidade(new BigDecimal(get(root, "quantidade")));
            estoque.setEstoque_minimo(new BigDecimal(get(root, "estoque_minimo")));
            estoque.setData_atualizacao (get(root, "data_atualizacao"));
            estoque.setId_produto(Integer.parseInt(get(root, "id_produto")));

            return estoque;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Estoque: " + e.getMessage());
        }
    }

    private String get(Element el, String tag) {
        NodeList list = el.getElementsByTagNameNS("*", tag);
        return list.getLength() > 0 ? list.item(0).getTextContent() : null;
    }

    private String pegarValor(Element elementoPai, String nomeTag) {
        NodeList listaEstoque = elementoPai.getElementsByTagNameNS("*", nomeTag);

        if (listaEstoque.getLength() == 0) {
            return null;
        }

        return listaEstoque.item(0).getTextContent();
    }
}



