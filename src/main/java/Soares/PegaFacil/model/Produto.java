package Soares.PegaFacil.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private double preco;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<ProdutoPedido> produtoPedidos;

    @ManyToMany
    @JoinTable(
      name = "produto_categorias",
      joinColumns = @JoinColumn(name = "produto_id"),
      inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Set<ProdutoPedido> getProdutoPedidos() {
        return produtoPedidos;
    }

    public void setProdutoPedidos(Set<ProdutoPedido> produtoPedidos) {
        this.produtoPedidos = produtoPedidos;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
}
