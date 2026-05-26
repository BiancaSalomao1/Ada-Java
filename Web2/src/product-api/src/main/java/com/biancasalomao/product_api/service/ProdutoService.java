package com.biancasalomao.product_api.service;

import com.biancasalomao.product_api.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private final List<Produto> produtos = new ArrayList<>(
            List.of(
                    new Produto(1L, "Produto 1", 10.0),
                    new Produto(2L, "Produto 2", 20.0),
                    new Produto(3L, "Produto 3", 30.0)
            )
    );

    private List<Produto> produtoList() {
        return produtos;
    }

    public List<Produto> getProdutos() {
        return produtoList();
    }

    public Produto buscarPorId(Long id) {

        return produtoList().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Produto não encontrado"
                        ));
    }

    public Produto criarProduto(Produto produto) {

        produtoList().add(produto);

        return produto;
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {

        return produtoList().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(produtoExistente -> {

                    produtoExistente.setNome(produtoAtualizado.getNome());
                    produtoExistente.setPreco(produtoAtualizado.getPreco());

                    return produtoExistente;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Produto não encontrado"
                        ));
    }

    public void deletarProduto(Long id) {

        Produto produto = buscarPorId(id);

        produtoList().remove(produto);
    }
}