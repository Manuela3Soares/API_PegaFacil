package Soares.PegaFacil.controller;

import Soares.PegaFacil.model.ProdutoPedido;
import Soares.PegaFacil.repository.ProdutoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/produto-pedidos")
public class ProdutoPedidoController {

    @Autowired
    private ProdutoPedidoRepository produtoPedidoRepository;

    @GetMapping
    public List<ProdutoPedido> getAllProdutoPedidos() {
        return produtoPedidoRepository.findAll();
    }

    @PostMapping
    public ProdutoPedido createProdutoPedido(@RequestBody ProdutoPedido produtoPedido) {
        return produtoPedidoRepository.save(produtoPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoPedido> getProdutoPedidoById(@PathVariable Long id) {
        ProdutoPedido produtoPedido = produtoPedidoRepository.findById(id).orElse(null);
        if (produtoPedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produtoPedido, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoPedido> updateProdutoPedido(@PathVariable Long id, @RequestBody ProdutoPedido produtoPedidoDetails) {
        ProdutoPedido produtoPedido = produtoPedidoRepository.findById(id).orElse(null);
        if (produtoPedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        produtoPedido.setQuantidade(produtoPedidoDetails.getQuantidade());
        final ProdutoPedido updatedProdutoPedido = produtoPedidoRepository.save(produtoPedido);
        return new ResponseEntity<>(updatedProdutoPedido, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProdutoPedido(@PathVariable Long id) {
        ProdutoPedido produtoPedido = produtoPedidoRepository.findById(id).orElse(null);
        if (produtoPedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        produtoPedidoRepository.delete(produtoPedido);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
