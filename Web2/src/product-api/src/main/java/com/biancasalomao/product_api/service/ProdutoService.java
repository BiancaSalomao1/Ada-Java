package com.biancasalomao.product_api.service;

import com.biancasalomao.product_api.model.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    public Produto produto = Produto.builder()
            .id(1L)
            .nome("Fone de Ouvido")
            .preco(100.0)
            .build();

    public Produto produto2 = Produto.builder()
            .id(2L)
            .nome("Mouse")
            .preco(50.0)
            .build();

    public Produto produto3 = Produto.builder()
            .id(3L)
            .nome("Teclado")
            .preco(80.0)
            .build();

    public List<Produto> getProdutos() {
        return List.of(produto, produto2, produto3);
    }
}