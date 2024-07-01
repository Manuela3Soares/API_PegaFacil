package Soares.PegaFacil.repository;

import Soares.PegaFacil.model.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {
}
