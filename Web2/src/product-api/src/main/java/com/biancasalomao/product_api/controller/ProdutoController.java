package com.biancasalomao.product_api.controller;

import com.biancasalomao.product_api.model.Produto;
import com.biancasalomao.product_api.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getProdutos() {

        return ResponseEntity.ok(produtoService.getProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {

        Produto produto = produtoService.getProdutos()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        return ResponseEntity.ok(produto);
    }

    @PostMapping()
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {

        return ResponseEntity.noContent().build();
    }

}