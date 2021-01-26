package org.condo.api.cadastro.exception;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.RollbackException;

public class CondoDAOException extends CondoException {

    public CondoDAOException() {
    }

    public CondoDAOException(Integer codigo) {
        super(codigo);
    }

    public CondoDAOException(String descricao) {
        super(descricao);
    }

    public CondoDAOException(Integer codigo, String descricao) {
        super(codigo, descricao);
    }

    public CondoDAOException(Throwable erro) {
        super(erro);
        if (erro instanceof NoResultException) {
            this.codigo = 1001;
            this.descricao = "Consulta não retornou nenhum resultado";
        } else if (erro instanceof NonUniqueResultException) {
            this.codigo = 1002;
            this.descricao = "Consulta retornou mais de um resultado possivel";
        }
    }

    public CondoDAOException(Throwable erro, Integer codigo) {
        super(erro, codigo);
    }

    public CondoDAOException(Throwable erro, String descricao) {
        super(erro, descricao);
    }

    public CondoDAOException(Throwable erro, Integer codigo, String descricao) {
        super(erro, codigo, descricao);
    }
}
