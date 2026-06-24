package com.soap.apirest.soap;

import com.soap.apirest.dto.*;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


@Component
public class SoapClient {

    private final RestTemplate restTemplate;
    private final SoapEnvelopeBuilder envelopeBuilder;

    private static final String SOAP_URL = "https://apisoap.pythonanywhere.com/";
    private static final String LOGIN_URL = "https://apisoap.pythonanywhere.com/login";

    private static final String USUARIO = "admines";
    private static final String SENHA = "admines777";

    public SoapClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.envelopeBuilder = new SoapEnvelopeBuilder();
    }

    public String listarClientes() {
        String xml = envelopeBuilder.criarEnvelopeSemParametro("GetClientes");
        return enviarRequisicaoSoap(xml, "GetClientes");
    }

    public String buscarClientePorId(Integer id) {
        String xml = envelopeBuilder.criarEnvelopeComId(
                "GetClienteById",
                "id_cliente",
                id
        );

        return enviarRequisicaoSoap(xml, "GetClienteById");
    }


    public String criarCliente(ClienteDTO cliente) {
        String xml = envelopeBuilder.criarEnvelopeCreateCliente(cliente);
        return enviarRequisicaoSoap(xml, "CreateCliente");
    }

    public String atualizarCliente(Integer id, ClienteDTO cliente) {
        String xml = envelopeBuilder.criarEnvelopeUpdateCliente(id, cliente);
        return enviarRequisicaoSoap(xml, "UpdateCliente");
    }

    public String deletarCliente(Integer id) {
        String xml = envelopeBuilder.criarEnvelopeDeleteCliente(id);
        return enviarRequisicaoSoap(xml, "DeleteCliente");
    }



    //Produtos


    public String listarProdutos(){
        String xml=envelopeBuilder.criarEnvelopeSemParametro("GetProdutos");
        return enviarRequisicaoSoap(xml, "GetProdutos");
    }

    public String criarProduto(ProdutoDTO produto) {
        String xml = envelopeBuilder.criarEnvelopeCreateProduto(produto);
        return enviarRequisicaoSoap(xml, "CreateProduto");
    }
    public String atualizarProduto(Integer id, ProdutoDTO produto) {
        String xml = envelopeBuilder.criarEnvelopeUpdateProduto(id, produto);
        return enviarRequisicaoSoap(xml, "UpdateProduto");
    }
    public String buscarProdutoPorId(Integer id) {
        String xml=envelopeBuilder.criarEnvelopeComId(
                "GetProdutoById",
                "id_produto",
                id
        );
        return enviarRequisicaoSoap(xml, "GetProdutoById");
    }

    public String deletarProduto(Integer id) {
        String xml = envelopeBuilder.criarEnvelopeDeleteProduto(id);
        return enviarRequisicaoSoap(xml, "DeleteProduto");
    }

    //Categoria

    public String listarCategorias(){
        String xml=envelopeBuilder.criarEnvelopeSemParametro("GetCategorias");
        return enviarRequisicaoSoap(xml, "GetCategorias");
    }

    public String listarCategoriasPorId(Integer id) {
        String xml=envelopeBuilder.criarEnvelopeComId("GetCategoriaById", "id_categoria",id);

        return  enviarRequisicaoSoap(xml, "GetCategoriaById");
    }

    public String criarCategoria(CategoriaDTO categoria) {
        String xml = envelopeBuilder.criarEnvelopeCreateCategoria(categoria);
        return enviarRequisicaoSoap(xml, "CreateCategoria");
    }

    public String atualizarCategoria(Integer id, CategoriaDTO categoria) {
        String xml = envelopeBuilder.criarEnvelopeUpdateCategoria(id, categoria);
        return enviarRequisicaoSoap(xml, "UpdateCategoria");
    }
    public String deletarCategoria(Integer id) {
        String xml = envelopeBuilder.criarEnvelopeDeleteCategoria(id);
        return enviarRequisicaoSoap(xml, "DeleteCategoria");
    }

    // Fornecedores


    public String listarFornecedores(){
        String xml=envelopeBuilder.criarEnvelopeSemParametro("GetFornecedores");
        return enviarRequisicaoSoap(xml, "GetFornecedores");
    }

    public String criarFornecedores(FornecedoresDTO fornecedores) {
        String xml=envelopeBuilder.criarEnvelopeCreateFornecedores(fornecedores);
       return enviarRequisicaoSoap(xml, "CreateFornecedores");
    }

    public String atualizarFornecedores(Integer id, FornecedoresDTO fornecedores) {
        String xml = envelopeBuilder.criarEnvelopeUpdateFornecedores(id, fornecedores);
        return enviarRequisicaoSoap(xml, "UpdateFornecedores");
    }
    public String buscarFornecedorPorId(Integer id) {
        String xml = envelopeBuilder.criarEnvelopeComId(
                "GetFornecedorById",
                "id_fornecedor",
                id
        );

        return enviarRequisicaoSoap(xml, "GetFornecedorById");
    }
    public String deletarFornecedor(Integer id) {
        String xml = envelopeBuilder.criarEnvelopeDeleteFornecedores(id);
        return enviarRequisicaoSoap(xml, "DeleteFornecedor");
    }



    //Vendedores

    public String listarVendedores(){
        String xml=envelopeBuilder.criarEnvelopeSemParametro("GetVendedores");
        return enviarRequisicaoSoap(xml, "GetVendedores");
    }

    public String buscarVendedorPorId(Integer id) {
        String xml=envelopeBuilder.criarEnvelopeComId(
                "GetVendedorById",
                "id_vendedor",
                id
        );
        return enviarRequisicaoSoap(xml, "GetVendedorById");
    }

    public String criarVendedores(VendedoresDTO vendedores) {
        String xml=envelopeBuilder.criarEnvelopeCreateVendedores(vendedores);
        return enviarRequisicaoSoap(xml, "CreateVendedores");
    }

    public String atualizarVendedores(Integer id, VendedoresDTO vendedores) {
        String xml = envelopeBuilder.criarEnvelopeUpdateVendedores(id, vendedores);
        return enviarRequisicaoSoap(xml, "UpdateVendedores");
    }
    public String deletarVendedores(Integer id) {
        String xml = envelopeBuilder.criarEnvelopeDeleteVendedores(id);
        return enviarRequisicaoSoap(xml, "DeleteVendedores");
    }

    // Estoque

    public String listarEstoque(){
        String xml=envelopeBuilder.criarEnvelopeSemParametro("GetEstoque");
        return enviarRequisicaoSoap(xml, "GetEstoque");
    }

    public String buscarEstoquePorId(Integer id) {
        String xml=envelopeBuilder.criarEnvelopeComId(
                "GetEstoqueById",
                "id_estoque",
                id
        );
        return enviarRequisicaoSoap(xml, "GetEstoqueById");
    }
    public String atualizarEstoque(Integer id, EstoqueDTO estoque) {
        String xml = envelopeBuilder.criarEnvelopeUpdateEstoque(id, estoque);
        return enviarRequisicaoSoap(xml, "UpdateEstoque");
    }


    public String criarEstoque(EstoqueDTO estoque) {
        String xml=envelopeBuilder.criarEnvelopeCreateEstoque(estoque);
        return enviarRequisicaoSoap(xml, "CreateEstoque");
    }
    public String deletarEstoque(Integer id) {
        String xml = envelopeBuilder.criarEnvelopeDeleteEstoque(id);
        return enviarRequisicaoSoap(xml, "DeleteEstoque");
    }




    // Pedidos

    public String listarPedidos(){
        String xml=envelopeBuilder.criarEnvelopeSemParametro("GetPedidos");
        return enviarRequisicaoSoap(xml, "GetPedidos");
    }

    public String buscarPedidosPorId(Integer id) {
        String xml=envelopeBuilder.criarEnvelopeComId(
                "GetPedidoById",
                "id_pedido",
                id
        );
        return enviarRequisicaoSoap(xml, "GetPedidoById");
    }

    public String criarPedido(PedidosDTO pedido) {
        String xml=envelopeBuilder.criarEnvelopeCreatePedidos(pedido);
        return enviarRequisicaoSoap(xml, "CreatePedido");
    }

    public String atualizarPedido(Integer id, PedidosDTO pedido) {
        String xml = envelopeBuilder.criarEnvelopeUpdatePedido(id, pedido);
        return enviarRequisicaoSoap(xml, "UpdatePedido");
    }

    public String deletarPedido(Integer id) {
        String xml = envelopeBuilder.criarEnvelopeDeletePedido(id);
        return enviarRequisicaoSoap(xml, "DeletePedido");
    }

    //Itens Pedido

    public String listarItensPedido(){
        String xml=envelopeBuilder.criarEnvelopeSemParametro("GetItensPedido");
        return enviarRequisicaoSoap(xml, "GetItensPedido");
    }

    public String buscarItensPedidoPorId(Integer id) {
        String xml=envelopeBuilder.criarEnvelopeComId(
                "GetItemPedidoById",
                "id_item",
                id
        );
        return enviarRequisicaoSoap(xml, "GetItemPedidoById");
    }

    public String criarItensPedido(ItensPedidoDTO pedido) {
        String xml=envelopeBuilder.criarEnvelopeCreateItemPedidos(pedido);
        return enviarRequisicaoSoap(xml, "CreateItemPedido");
    }

    public String atualizarItensPedido(Integer id, ItensPedidoDTO pedido) {
        String xml = envelopeBuilder.criarEnvelopeUpdateItemPedido(id, pedido);
        return enviarRequisicaoSoap(xml, "UpdateItemPedido");
    }

    public String deletarItensPedido(Integer id) {
        String xml = envelopeBuilder.criarEnvelopeDeleteItemPedido(id);
        return enviarRequisicaoSoap(xml, "DeleteItemPedido");
    }








    //token


    private String gerarToken() {

        Map<String, String> login = Map.of(
                "usuario", USUARIO,
                "senha", SENHA
        );

        ResponseEntity<Map> response = restTemplate.postForEntity(
                LOGIN_URL,
                login,
                Map.class
        );

        if (response.getBody() == null || response.getBody().get("access_token") == null) {
            throw new RuntimeException("Token não foi retornado pela API SOAP.");
        }

        return response.getBody().get("access_token").toString();
    }

    private String enviarRequisicaoSoap(String xml, String soapAction) {

        System.out.println("\n================ XML ENVIADO ================");
        System.out.println(xml);
        System.out.println("SOAPAction: " + soapAction);
        System.out.println("=============================================\n");

        String token = gerarToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.setAccept(List.of(MediaType.TEXT_XML));
        headers.set("Authorization", "Bearer " + token);
        headers.set("SOAPAction", "\"" + soapAction + "\"");

        byte[] body = xml.getBytes(StandardCharsets.UTF_8);

        HttpEntity<byte[]> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                SOAP_URL,
                HttpMethod.POST,
                request,
                String.class
        );

        return response.getBody();
    }
}