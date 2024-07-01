package Soares.PegaFacil.controller;

import Soares.PegaFacil.model.Categoria;
import Soares.PegaFacil.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoriaDetails) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoria.setNome(categoriaDetails.getNome());
        final Categoria updatedCategoria = categoriaRepository.save(categoria);
        return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoriaRepository.delete(categoria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
