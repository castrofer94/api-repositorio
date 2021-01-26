package org.condo.api.cadastro.entidade;

import javax.persistence.*;

@Entity
@Table(name = "TB_TIPO_PRODUTO")
public class TipoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TP_ID")
    private Long id;

    @Column(name = "TP_NOME_CATEGORIA")
    private String nomeCategoria;

    @Column(name = "TP_DESCRICAO")
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
