package org.condo.api.cadastro.as;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.condo.api.cadastro.entidade.Condominio;
import org.condo.api.cadastro.entidade.dao.GenericDao;
import org.condo.api.cadastro.exception.CondoException;
import org.postgresql.util.PSQLException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@LocalBean
@Stateless
public class CondominioAS {

    private final Logger log = LogManager.getLogger(getClass());

    @EJB
    GenericDao dao;

    public Condominio novoCondominio(Condominio condominio) throws CondoException {
        log.info("Criando novo condominio");
        if (condominio.getId() != null) {
            dao.atualizar(condominio);
        } else {
            dao.salvar(condominio);
        }
        return condominio;
    }

    public List<Condominio> listaTodosCondominio() throws CondoException {
        log.info("Listando todos os condominios do sistema");
        return dao.getList(Condominio.class);
    }
}
