package org.condo.api.cadastro.entidade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_CONDOMINIO")
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_ID")
    private Long id;

    @Column(name = "CD_NOME")
    private String nome;

    @Column(name = "CD_UF")
    private String uf;

    @Column(name = "CD_MUNICIPIO")
    private String municipio;

    @ManyToOne
    @JoinColumn(name = "CD_USUARIO_CADASTRO")
    private Usuario usuarioCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CD_DATA_CADASTRO")
    private Date dataCadastro;

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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
