package org.condo.api.cadastro.entidade;

import javax.persistence.*;

@Entity
@Table(name = "TB_CATEGORIA_VENDA")
public class CategoriaVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CV_ID")
    private Long id;

    @Column(name = "CV_NOME")
    private String nome;

    @Column(name = "CV_DESCRICAO")
    private String descricao;

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
}
