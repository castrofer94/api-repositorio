package org.condo.api.cadastro.as;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.condo.api.cadastro.entidade.CategoriaVenda;
import org.condo.api.cadastro.entidade.dao.GenericDao;
import org.condo.api.cadastro.exception.CondoException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@LocalBean
@Stateless
public class CategoriaVendaAS {

    private final Logger log = LogManager.getLogger(getClass());

    @EJB
    GenericDao dao;

    public CategoriaVenda novoCategoriaVenda(CategoriaVenda categoriaVenda) throws CondoException {
        log.info("Criando nova categoria de venda");
        if (categoriaVenda.getId() != null) {
            dao.atualizar(categoriaVenda);
        } else {
            dao.salvar(categoriaVenda);
        }
        return categoriaVenda;
    }

    public List<CategoriaVenda> listaTodasCategoriaVenda() throws CondoException {
        log.info("Listando todos as categorias de vendas do sistema");
        return dao.getList(CategoriaVenda.class);
    }
}
