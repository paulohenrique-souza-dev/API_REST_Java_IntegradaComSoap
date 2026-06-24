package com.soap.apirest.service;

import com.soap.apirest.dto.ProdutoDTO;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoXmlParser {


    // LISTA DE PRODUTOS

    public List<ProdutoDTO> converterListaProduto(String xml) {
        List<ProdutoDTO> produtos = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document document = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            NodeList lista = document.getElementsByTagNameNS("*", "Produto");

            System.out.println("Total de produtos encontrados no XML: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++) {

                Element produtoElement = (Element) lista.item(i);

                ProdutoDTO produto = new ProdutoDTO();

                produto.setIdProduto(converterInteger(pegarValor(produtoElement, "id_produto")));
                produto.setNomeProduto(pegarValor(produtoElement, "nome_produto"));
                produto.setPreco(converterBigDecimal(pegarValor(produtoElement, "preco")));
                produto.setCusto(converterBigDecimal(pegarValor(produtoElement, "custo")));
                produto.setIdCategoria(converterInteger(pegarValor(produtoElement, "id_categoria")));
                produto.setIdFornecedor(converterInteger(pegarValor(produtoElement, "id_fornecedor")));

                produtos.add(produto);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter lista de produtos: " + e.getMessage(), e);
        }

        return produtos;
    }


    // PRODUTO POR ID

    public ProdutoDTO converterProdutoUnico(String xml) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document doc = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            Element root = (Element) doc
                    .getElementsByTagNameNS("*", "GetProdutoByIdResult")
                    .item(0);

            if (root == null) return null;

            ProdutoDTO p = new ProdutoDTO();

            p.setIdProduto(converterInteger(pegarValor(root, "id_produto")));
            p.setNomeProduto(pegarValor(root, "nome_produto"));
            p.setPreco(converterBigDecimal(pegarValor(root, "preco")));
            p.setCusto(converterBigDecimal(pegarValor(root, "custo")));
            p.setIdCategoria(converterInteger(pegarValor(root, "id_categoria")));
            p.setIdFornecedor(converterInteger(pegarValor(root, "id_fornecedor")));

            return p;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter produto único: " + e.getMessage(), e);
        }
    }


    // PEGAR VALOR XML

    private String pegarValor(Element elementoPai, String nomeTag) {

        NodeList lista = elementoPai.getElementsByTagNameNS("*", nomeTag);

        if (lista.getLength() == 0 || lista.item(0) == null) {
            return null;
        }

        return lista.item(0).getTextContent();
    }


    // CONVERSÕES

    private Integer converterInteger(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        return Integer.parseInt(valor);
    }

    private BigDecimal converterBigDecimal(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        return new BigDecimal(valor);
    }
}