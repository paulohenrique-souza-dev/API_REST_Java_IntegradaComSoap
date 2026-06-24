package com.soap.apirest.soap;
import com.soap.apirest.dto.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SoapEnvelopeBuilder {

    private static final String NAMESPACE = "http://apisoap.pythonanywhere.com";

    public String criarEnvelopeSemParametro(String metodo) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:%s></tns:%s>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, metodo, metodo);
    }

    public String criarEnvelopeComId(String metodo, String nomeParametro, Integer id) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:%s>
                            <tns:%s>%d</tns:%s>
                        </tns:%s>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, metodo, nomeParametro, id, nomeParametro, metodo);
    }

    private String valorXml(String valor) {
        return valor == null ? "" : valor;
    }

    private String valorXml(Integer valor) {
        return valor == null ? "" : valor.toString();
    }

    private String valorXml(BigDecimal valor) {
        return  valor == null ? "" : valor.toString();
    }
    private String valorDataXml(Date data) {
        if (data == null) {
            return "";
        }

        return new SimpleDateFormat("yyyy-MM-dd").format(data);
    }

    public String criarEnvelopeCreateCliente(ClienteDTO cliente) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:tns="%s">
                <soapenv:Header/>
                <soapenv:Body>
                    <tns:CreateCliente>
                        <tns:nome>%s</tns:nome>
                        <tns:cidade>%s</tns:cidade>
                        <tns:data_cadastro>%s</tns:data_cadastro>
                        <tns:tipo_cliente>%s</tns:tipo_cliente>
                    </tns:CreateCliente>
                </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(
                NAMESPACE,
                valorXml(cliente.getNome()),
                valorXml(cliente.getCidade()),
                valorXml(cliente.getDataCadastro()),
                valorXml(cliente.getTipoCliente())
        );
    }
    public String criarEnvelopeUpdateCliente(Integer id, ClienteDTO cliente) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:UpdateCliente>
                            <tns:id_cliente>%d</tns:id_cliente>
                            <tns:nome>%s</tns:nome>
                            <tns:cidade>%s</tns:cidade>
                            <tns:data_cadastro>%s</tns:data_cadastro>
                            <tns:tipo_cliente>%s</tns:tipo_cliente>
                        </tns:UpdateCliente>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(
                NAMESPACE,
                id,
                cliente.getNome(),
                cliente.getCidade(),
                cliente.getDataCadastro(),
                cliente.getTipoCliente()
        );
    }

    public String criarEnvelopeDeleteCliente(Integer id) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:DeleteCliente>
                            <tns:id_cliente>%d</tns:id_cliente>
                        </tns:DeleteCliente>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, id);
    }


    public String criarEnvelopeCreateProduto(ProdutoDTO produto) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:tns="%s">
                <soapenv:Header/>
                <soapenv:Body>
                    <tns:CreateProduto>
                        <tns:nome_produto>%s</tns:nome_produto>
                        <tns:preco>%s</tns:preco>
                        <tns:custo>%s</tns:custo>
                         <tns:id_categoria>%s</tns:id_categoria>
                        <tns:id_fornecedor>%s</tns:id_fornecedor>
                    </tns:CreateProduto>
                </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(
                NAMESPACE,
                valorXml(produto.getNomeProduto()),
                valorXml(produto.getPreco()),
                valorXml(produto.getCusto()),
                valorXml(produto.getIdCategoria()),
                valorXml(produto.getIdFornecedor())
        );
    }


    public String criarEnvelopeUpdateProduto(Integer id, ProdutoDTO produto) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:tns="%s">
                <soapenv:Header/>
                <soapenv:Body>
                    <tns:UpdateProduto>
                    <tns:id_produto>%s</tns:id_produto>
                        <tns:nome_produto>%s</tns:nome_produto>
                        <tns:preco>%s</tns:preco>
                        <tns:custo>%s</tns:custo>
                         <tns:id_categoria>%s</tns:id_categoria>
                        <tns:id_fornecedor>%s</tns:id_fornecedor>
                    </tns:UpdateProduto>
                </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(
                NAMESPACE,
                valorXml(id),
                valorXml(produto.getNomeProduto()),
                valorXml(produto.getPreco()),
                valorXml(produto.getCusto()),
                valorXml(produto.getIdCategoria()),
                valorXml(produto.getIdFornecedor())
        );
    }

    public String criarEnvelopeDeleteProduto(Integer id) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:DeleteProduto>
                            <tns:id_produto>%d</tns:id_produto>
                        </tns:DeleteProduto>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, id);
    }


    //Categoria
    public String criarEnvelopeCreateCategoria(CategoriaDTO categoria) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:tns="%s">
                <soapenv:Header/>
                <soapenv:Body>
                    <tns:CreateCategoria>
                    <tns:nome_categoria>%s</tns:nome_categoria>
                    </tns:CreateCategoria>
                </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(
                NAMESPACE,

                valorXml(categoria.getNomeCategoria())

        );
    }

    public String criarEnvelopeUpdateCategoria(Integer id, CategoriaDTO categoria) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:tns="%s">
                <soapenv:Header/>
                <soapenv:Body>
                    <tns:UpdateCategoria>
                    <tns:id_categoria>%s</tns:id_categoria>
                        <tns:nome_categoria>%s</tns:nome_categoria>
                    </tns:UpdateCategoria>
                </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(
                NAMESPACE,
                valorXml(id),
                valorXml(categoria.getIdCategoria()),
                valorXml(categoria.getNomeCategoria())

        );
    }

    public String criarEnvelopeDeleteCategoria(Integer id) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:DeleteCategoria>
                            <tns:id_categoria>%d</tns:id_categoria>
                        </tns:DeleteCategoria>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, id);
    }


    //fornecedores

    public String criarEnvelopeCreateFornecedores(FornecedoresDTO fornecedores) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:tns="%s">
                <soapenv:Header/>
                <soapenv:Body>
                    <tns:CreateFornecedor>
                        <tns:nome_fornecedor>%s</tns:nome_fornecedor>
                        <tns:cidade>%s</tns:cidade>
                        <tns:estado>%s</tns:estado>
                    </tns:CreateFornecedor>
                </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(
                NAMESPACE,
                valorXml(fornecedores.getNomeFornecedor()),
                valorXml(fornecedores.getCidade()),
                valorXml(fornecedores.getEstado())

        );
    }

    public String criarEnvelopeUpdateFornecedores(Integer id, FornecedoresDTO fornecedores) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:tns="%s">
                <soapenv:Header/>
                <soapenv:Body>
                    <tns:UpdateFornecedor>
                    <tns:id_fornecedor>%s</tns:id_fornecedor>
                        <tns:nome_fornecedor>%s</tns:nome_fornecedor>
                        <tns:cidade>%s</tns:cidade>
                        <tns:estado>%s</tns:estado>
                    </tns:UpdateFornecedor>
                </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(
                NAMESPACE,
                valorXml(id),
                valorXml(fornecedores.getNomeFornecedor()),
                valorXml(fornecedores.getCidade()),
                valorXml(fornecedores.getEstado())

        );
    }
    public String criarEnvelopeDeleteFornecedores(Integer id) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:DeleteFornecedor>
                            <tns:id_fornecedor>%d</tns:id_fornecedor>
                        </tns:DeleteFornecedor>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, id);
    }


    //Vendedores

    public String criarEnvelopeCreateVendedores(VendedoresDTO vendedores) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:tns="%s">
                <soapenv:Header/>
                <soapenv:Body>
                    <tns:CreateVendedor>
                        <tns:nome>%s</tns:nome>
                        <tns:regiao>%s</tns:regiao>
                        <tns:salario>%s</tns:salario>
                        <tns:data_admissao>%s</tns:data_admissao>
                    </tns:CreateVendedor>
                </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(
                NAMESPACE,
                valorXml(vendedores.getNomeVendedor()),
                valorXml(vendedores.getRegiao()),
                valorXml(vendedores.getSalario()),
                valorXml(vendedores.getDataAdmissao())

        );
    }


    public String criarEnvelopeUpdateVendedores(Integer id, VendedoresDTO vendedores) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:tns="%s">
                <soapenv:Header/>
                <soapenv:Body>
                    <tns:UpdateVendedor>
                    <tns:id_vendedor>%s</tns:id_vendedor>
                        <tns:nome>%s</tns:nome>
                        <tns:regiao>%s</tns:regiao>
                        <tns:salario>%s</tns:salario>
                        <tns:data_admissao>%s</tns:data_admissao>
                    </tns:UpdateVendedor>
                </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(
                NAMESPACE,
                valorXml(id),
                valorXml(vendedores.getNomeVendedor()),
                valorXml(vendedores.getRegiao()),
                valorXml(vendedores.getSalario()),
                valorXml(vendedores.getDataAdmissao())


        );
    }
    public String criarEnvelopeDeleteVendedores(Integer id) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:DeleteVendedor>
                            <tns:id_vendedor>%d</tns:id_vendedor>
                        </tns:DeleteVendedor>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, id);
    }

    //Estoque

    public String criarEnvelopeCreateEstoque(EstoqueDTO estoque) {
        return """
            <?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:tns="%s">
                <soapenv:Header/>
                <soapenv:Body>
                    <tns:CreateEstoque>
                        <tns:quantidade>%s</tns:quantidade>
                        <tns:estoque_minimo>%s</tns:estoque_minimo>
                        <tns:data_atualizacao>%s</tns:data_atualizacao>
                        <tns:id_produto>%s</tns:id_produto>
                    </tns:CreateEstoque>
                </soapenv:Body>
            </soapenv:Envelope>
            """.formatted(
                NAMESPACE,
                valorXml(estoque.getQuantidade()),
                valorXml(estoque.getEstoque_minimo()),
                valorXml(estoque.getData_atualizacao()),
                valorXml(estoque.getId_produto())

        );
    }

    public String criarEnvelopeUpdateEstoque(Integer id, EstoqueDTO estoque) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:UpdateEstoque>
                            <tns:id_estoque>%d</tns:id_estoque>
                            <tns:quantidade>%s</tns:quantidade>
                            <tns:estoque_minimo>%s</tns:estoque_minimo>
                            <tns:data_atualizacao>%s</tns:data_atualizacao>
                            <tns:id_produto>%s</tns:id_produto>
                        </tns:UpdateEstoque>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(
                NAMESPACE,
                id,
                estoque.getId_estoque(),
                estoque.getQuantidade(),
                estoque.getEstoque_minimo(),
                estoque.getData_atualizacao(),
                estoque.getId_produto()
        );
    }

    public String criarEnvelopeDeleteEstoque(Integer id) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:DeleteEstoque>
                            <tns:id_estoque>%d</tns:id_estoque>
                        </tns:Deleteestoque>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, id);


}



//      Pedidos
    public String criarEnvelopeCreatePedidos(PedidosDTO pedido) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:CreatePedido>
                            <tns:data_pedido>%s</tns:data_pedido>
                            <tns:status_pedido>%s</tns:status_pedido>
                            <tns:id_cliente>%s</tns:id_cliente>
                            <tns:id_vendedor>%s</tns:id_vendedor>
                        </tns:CreatePedido>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(
                NAMESPACE,
                valorXml(pedido.getDataPedido()),
                valorXml(pedido.getStatusPedido()),
                valorXml(pedido.getIdCliente()),
                valorXml(pedido.getIdVendedor())

        );
    }

    public String criarEnvelopeUpdatePedido(Integer id, PedidosDTO pedido) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:UpdatePedido>
                            <tns:id_pedido>%d</tns:id_pedido>
                            <tns:data_pedido>%s</tns:data_pedido>
                            <tns:status_pedido>%s</tns:status_pedido>
                            <tns:id_cliente>%s</tns:id_cliente>
                            <tns:id_vendedor>%s</tns:id_vendedor>
                        </tns:UpdatePedido>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(
                NAMESPACE,
                id,
                pedido.getIdPedido(),
                pedido.getDataPedido(),
                pedido.getStatusPedido(),
                pedido.getIdCliente(),
                pedido.getIdVendedor()
        );
    }

    public String criarEnvelopeDeletePedido(Integer id) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:DeletePedido>
                            <tns:id_pedido>%d</tns:id_pedido>
                        </tns:DeletePedido>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, id);


    }



    // Itens pedido

    public String criarEnvelopeCreateItemPedidos(ItensPedidoDTO item_pedido) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:CreateItemPedido>
                            <tns:quantidade>%s</tns:qauntidade>
                            <tns:valor_unitario>%s</tns:valor_unitario>
                            <tns:id_pedido>%s</tns:id_pedido>
                            <tns:id_produto>%s</tns:id_produto>
                        </tns:CreateItemPedido>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(
                NAMESPACE,
                valorXml(item_pedido.getQuantidade()),
                valorXml(item_pedido.getValorUnitario()),
                valorXml(item_pedido.getIdPedido()),
                valorXml(item_pedido.getIdproduto())

        );
    }

    public String criarEnvelopeUpdateItemPedido(Integer id, ItensPedidoDTO pedido_update) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:UpdateItemPedido>
                            <tns:id_item>%d</tns:id_item>
                            <tns:quantidade>%s</tns:quantidade>
                            <tns:valor_unitario>%s</tns:valor_unitario>
                            <tns:id_pedido>%s</tns:id_pedido>
                            <tns:id_produto>%s</tns:id_produto>
                        </tns:UpdateItemPedido>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(
                NAMESPACE,
                id,
                pedido_update.getIdItem(),
                pedido_update.getQuantidade(),
                pedido_update.getValorUnitario(),
                pedido_update.getIdPedido(),
                pedido_update.getIdproduto()
        );
    }

    public String criarEnvelopeDeleteItemPedido(Integer id) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:tns="%s">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <tns:DeleteItemPedido>
                            <tns:id_item>%d</tns:id_item>
                        </tns:DeleteItemPedido>
                    </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, id);


    }







}