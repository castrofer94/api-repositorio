package org.condo.api.cadastro.entidade;

import javax.persistence.*;

@Entity
@Table(name = "TB_PRODUTO_VENDA")
public class ProdutoVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PV_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PV_TIPO", nullable = false)
    private TipoProduto tipoProduto;

    @ManyToOne
    @JoinColumn(name = "PV_CATEGORIA", nullable = false)
    private CategoriaVenda categoriaVenda;

    @Column(name = "PV_TITULO")
    private String titulo;

    @Column(name = "PV_VALOR")
    private String valor;

    @Column(name = "PV_DESCRICAO")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "PV_TIPO_RETIRADA")
    private TipoRetirada tipoRetirada;

    @Enumerated(EnumType.STRING)
    @Column(name = "PV_TIPO_PAGAMENTO")
    private TipoPagamento tipoPagamento;

    @Column(name = "PV_HORARIO_ATENDIMENTO")
    private String horarioAtendimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "PV_DISPONIBILIDADE")
    private Disponibilidade disponibilidade;

    @ManyToOne
    @JoinColumn(name = "PV_USUARIO_CADASTRO")
    private Usuario usuarioCadastro;

    @ManyToOne
    @JoinColumn(name = "PV_CONDOMINIO")
    private Condominio condominio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public CategoriaVenda getCategoriaVenda() {
        return categoriaVenda;
    }

    public void setCategoriaVenda(CategoriaVenda categoriaVenda) {
        this.categoriaVenda = categoriaVenda;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoRetirada getTipoRetirada() {
        return tipoRetirada;
    }

    public void setTipoRetirada(TipoRetirada tipoRetirada) {
        this.tipoRetirada = tipoRetirada;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public void setHorarioAtendimento(String horarioAtendimento) {
        this.horarioAtendimento = horarioAtendimento;
    }

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public enum TipoRetirada {
        SOMENTE_ENTREGA,
        SOMENTE_RETIRADA,
        ENTRADA_RETIRADA
    }

    public enum TipoPagamento {
        SOMENTE_DINHEIRO,
        SOMENTE_CARTAO,
        DINHEIRO_CARTAO
    }

    public enum Disponibilidade {
        SOMENTE_ENCOMENDA,
        SOMENTE_PRONTA_ENTREGA,
        PRONTA_ENTREGA_ENCOMENDA
    }
}
