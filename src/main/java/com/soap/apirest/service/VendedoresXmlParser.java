package com.soap.apirest.service;

import com.soap.apirest.dto.VendedoresDTO;
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
public class VendedoresXmlParser {

    public List<VendedoresDTO> converterListaVendedores(String xml) {
        List<VendedoresDTO> listaVendedores = new ArrayList<>();

        try {
            DocumentBuilderFactory FactoryVendedores = DocumentBuilderFactory.newInstance();
            FactoryVendedores.setNamespaceAware(true);


            Document document = FactoryVendedores
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));


            NodeList lista = document.getElementsByTagNameNS("*", "Vendedor");
            System.out.println("TOTAL Vendedores ENCONTRADOS NO XML: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++) {
                Element vendedoresElement = (Element) lista.item(i);

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

                VendedoresDTO vendedores = new VendedoresDTO();
                vendedores.setIdVendedor(Integer.parseInt(pegarValor(vendedoresElement, "id_vendedor")));
                vendedores.setNomeVendedor(pegarValor(vendedoresElement, "nome"));
                vendedores.setregiao(pegarValor(vendedoresElement, "regiao"));
                vendedores.setSalario(new BigDecimal(pegarValor(vendedoresElement, "salario")));
                vendedores.setDataAdmissao(pegarValor(vendedoresElement, "data_admissao"));


                listaVendedores.add(vendedores);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter XML de vendedores: " + e.getMessage());
        }

        return listaVendedores;
    }


    public VendedoresDTO converterVendedorUnico(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            Document doc = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            Element root = (Element) doc.getElementsByTagNameNS("*", "GetVendedorByIdResult").item(0);

            if (root == null) return null;

            VendedoresDTO v = new VendedoresDTO();
            v.setIdVendedor(Integer.parseInt(get(root, "id_vendedor")));
            v.setNomeVendedor(get(root, "nome"));
            v.setregiao(get(root, "regiao"));
            v.setSalario(new BigDecimal(get(root, "salario")));
            v.setDataAdmissao(get(root, "data_admissao"));

            return v;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter vendedor: " + e.getMessage());
        }
    }

    private String get(Element el, String tag) {
        NodeList list = el.getElementsByTagNameNS("*", tag);
        return list.getLength() > 0 ? list.item(0).getTextContent() : null;
    }

    private String pegarValor(Element elementoPai, String nomeTag) {
        NodeList listaVendedores = elementoPai.getElementsByTagNameNS("*", nomeTag);

        if (listaVendedores.getLength() == 0) {
            return null;
        }

        return listaVendedores.item(0).getTextContent();
    }
}




            
            
            
            
            
            
            
            
            
            
            
