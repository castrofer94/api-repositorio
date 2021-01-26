package org.condo.api.cadastro.entidade;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_USUARIO")
@NamedQueries({
        @NamedQuery(name = "Busca.porEmail",
                query = "select us from Usuario us where us.email = :p0")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "US_ID")
    private Long id;

    @Column(name = "US_NOME")
    private String nome;

    @Column(name = "US_EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "US_SENHA", nullable = false)
    private String senha;

    @Column(name = "US_DATA_CRIACAO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacriacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "US_STATUS", columnDefinition = "VARCHAR(255) DEFAULT 'ATIVO'")
    private StatusUsuario status;

    @Column(name = "US_TELEFONE")
    private String telefone;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public enum StatusUsuario {
        ATIVO, BLOQUEADO, INATIVO
    }
}
