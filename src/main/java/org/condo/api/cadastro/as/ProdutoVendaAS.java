package org.condo.api.cadastro.as;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.condo.api.cadastro.entidade.ProdutoVenda;
import org.condo.api.cadastro.entidade.dao.GenericDao;
import org.condo.api.cadastro.exception.CondoException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@LocalBean
@Stateless
public class ProdutoVendaAS {

    private final Logger log = LogManager.getLogger(getClass());

    @EJB
    GenericDao dao;

    public ProdutoVenda novoProdutoVenda(ProdutoVenda tipoProduto) throws CondoException {
        log.info("Criando novo produto de venda");
        if (tipoProduto.getId() != null) {
            dao.atualizar(tipoProduto);
        } else {
            dao.salvar(tipoProduto);
        }
        return tipoProduto;
    }

    public List<ProdutoVenda> listaTodosProdutoVenda() throws CondoException {
        log.info("Listando todos os produtos de venda do sistema");
        return dao.getList(ProdutoVenda.class);
    }
}
