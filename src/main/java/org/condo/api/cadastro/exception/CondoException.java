package org.condo.api.cadastro.exception;

import javax.persistence.NoResultException;

public class CondoException extends Exception {
    protected Throwable erro;
    protected Integer codigo;
    protected String descricao;

    public CondoException() {
    }

    public CondoException(Integer codigo) {
        this.codigo = codigo;
    }

    public CondoException(String descricao) {
        this.descricao = descricao;
    }

    public CondoException(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public CondoException(Throwable erro) {
        super(erro);
        this.erro = erro;
    }

    public CondoException(Throwable erro, Integer codigo) {
        this.erro = erro;
        this.codigo = codigo;
    }

    public CondoException(Throwable erro, String descricao) {
        super(erro);
        this.erro = erro;
        this.descricao = descricao;
    }

    public CondoException(Throwable erro, Integer codigo, String descricao) {
        this.codigo = codigo;
        this.erro = erro;
        this.descricao = descricao;
    }

    public void setErro(Throwable erro) {
        this.erro = erro;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
