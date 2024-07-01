package Soares.PegaFacil.controller;

import Soares.PegaFacil.model.Produto;
import Soares.PegaFacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetails) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        produto.setNome(produtoDetails.getNome());
        produto.setDescricao(produtoDetails.getDescricao());
        produto.setPreco(produtoDetails.getPreco());
        final Produto updatedProduto = produtoRepository.save(produto);
        return new ResponseEntity<>(updatedProduto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduto(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        produtoRepository.delete(produto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
