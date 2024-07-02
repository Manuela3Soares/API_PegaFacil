package Soares.PegaFacil.controller;

import Soares.PegaFacil.model.Pedido;
import Soares.PegaFacil.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedidoDetails) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pedido.setData(pedidoDetails.getData());
        final Pedido updatedPedido = pedidoRepository.save(pedido);
        return new ResponseEntity<>(updatedPedido, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePedido(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pedidoRepository.delete(pedido);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
