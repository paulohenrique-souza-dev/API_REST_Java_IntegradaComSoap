package com.soap.apirest.service;

import com.soap.apirest.dto.CategoriaDTO;
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
public class CategoriaXmlParser {

    public List<CategoriaDTO> converterCategoriaXML(String xml) {
        List<CategoriaDTO> categorias = new ArrayList<>();

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document document=factory
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            NodeList lista=document.getElementsByTagNameNS("*","Categoria");

            System.out.println("Total de Categorias" + lista.getLength());

            for (int i=0;i<lista.getLength();i++){
                Element categoriaElement=(Element)lista.item(i);
                CategoriaDTO categoria = new CategoriaDTO();
                categoria.setIdCategoria(Integer.parseInt(pegarValor(categoriaElement, "id_categoria")));
                categoria.setNomeCategoria(pegarValor(categoriaElement, "nome_categoria"));

                categorias.add(categoria);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro ao converter XML de Categoria" + e.getMessage());
        }
        return categorias;

    }

    public CategoriaDTO converterCategoriaUnico(String xml) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document doc = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            Element root = (Element) doc
                    .getElementsByTagNameNS("*", "GetCategoriaByIdResult")
                    .item(0);

            if (root == null) return null;

            CategoriaDTO categoria = new CategoriaDTO();

            categoria.setIdCategoria(converterInteger(pegarValor(root, "id_categoria")));
            categoria.setNomeCategoria(pegarValor(root, "nome_categoria"));
            return categoria;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Categoria única: " + e.getMessage(), e);
        }
    }

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

    private String pegarValor(Element elementopai2, String nomeTag){
        NodeList lista= elementopai2.getElementsByTagNameNS("*", nomeTag);

        if(lista.getLength() == 0){
            return  null;
        }
        return lista.item(0).getTextContent();
    }
}

