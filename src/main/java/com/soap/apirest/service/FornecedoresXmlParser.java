package com.soap.apirest.service;

import com.soap.apirest.dto.FornecedoresDTO;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedoresXmlParser {

    public List<FornecedoresDTO> converterListaFornecedores(String xml){
        List<FornecedoresDTO> listaFornecedores = new ArrayList<>();

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document document=factory
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            NodeList lista=document.getElementsByTagNameNS("*", "Fornecedor");
            System.out.println("TOTAL FORNECEDORES NO XML: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++) {
                Element fornecedoresElement = (Element) lista.item(i);

                FornecedoresDTO fornecedores = new FornecedoresDTO();
                fornecedores.setIdFornecedor(Integer.parseInt(pegarValor(fornecedoresElement, "id_fornecedor")));
                fornecedores.setNomeFornecedor(pegarValor(fornecedoresElement, "nome_fornecedor"));
                fornecedores.setCidade(pegarValor(fornecedoresElement, "cidade"));
                fornecedores.setEstado(pegarValor(fornecedoresElement, "estado"));


                listaFornecedores.add(fornecedores);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter XML de Fornecedores: " + e.getMessage());
        }

        return listaFornecedores;
    }

    private String pegarValor(Element elementoPai, String nomeTag) {
        NodeList lista = elementoPai.getElementsByTagNameNS("*", nomeTag);

        if (lista.getLength() == 0) {
            return null;
        }

        return lista.item(0).getTextContent();
    }
}













