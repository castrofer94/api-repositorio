package org.condo.api.cadastro.as;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.condo.api.cadastro.entidade.TipoProduto;
import org.condo.api.cadastro.entidade.dao.GenericDao;
import org.condo.api.cadastro.exception.CondoException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@LocalBean
@Stateless
public class TipoProdutoAS {

    private final Logger log = LogManager.getLogger(getClass());

    @EJB
    GenericDao dao;

    public TipoProduto novoTipoProduto(TipoProduto tipoProduto) throws CondoException {
        log.info("Criando novo tipo de produto");
        if (tipoProduto.getId() != null) {
            dao.atualizar(tipoProduto);
        } else {
            dao.salvar(tipoProduto);
        }
        return tipoProduto;
    }

    public List<TipoProduto> listaTodosTiposProdutos() throws CondoException {
        log.info("Listando todos os tipos de produtos do sistema");
        return dao.getList(TipoProduto.class);
    }
}
