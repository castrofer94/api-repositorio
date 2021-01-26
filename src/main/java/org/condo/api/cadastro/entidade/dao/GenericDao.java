package org.condo.api.cadastro.entidade.dao;

import org.condo.api.cadastro.exception.CondoDAOException;
import org.condo.api.cadastro.exception.CondoException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@LocalBean
@Stateless
public class GenericDao<T, I extends Serializable> {

    @PersistenceContext(name = "CondoShopPU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public T salvar(T entity) throws CondoException {
        try {
            getEntityManager().persist(entity);
        } catch (Exception e) {
            throw new CondoDAOException(e);
        }
        return entity;
    }

    public T atualizar(T entity) throws CondoException {
        try {
            getEntityManager().merge(entity);
        } catch (Exception e) {
            throw new CondoDAOException(e);
        }
        return entity;
    }

    public void remover(I id, Class classe) throws CondoException {
        try {
            T entity = findById(id, classe);
            T mergedEntity = getEntityManager().merge(entity);
            getEntityManager().remove(mergedEntity);
        } catch (Exception e) {
            throw new CondoDAOException(e);
        }
    }

    public List<T> getList(Class persistedClass) throws CondoException {
        try {
            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(persistedClass);
            query.from(persistedClass);
            return getEntityManager().createQuery(query).getResultList();
        } catch (Exception e) {
            throw new CondoDAOException(e);
        }
    }

    public T findById(I id, Class persistedClass) throws CondoException {
        try {
            return (T) getEntityManager().find(persistedClass, id);
        } catch (Exception e) {
            throw new CondoDAOException(e);
        }
    }

    public List<T> buscarListNamedQuery(String namedQuery, Class classe, Object... var) throws CondoException {
        try {
            return this.buscarNamedQuery(namedQuery, classe, var).getResultList();
        } catch (Exception e) {
            throw new CondoDAOException(e);
        }
    }

    public T buscarEntidadeNamedQuery(String namedQuery, Class classe, Object... var) throws CondoException {
        try {
            return (T) this.buscarNamedQuery(namedQuery, classe, var).getSingleResult();
        } catch (Exception e) {
            throw new CondoDAOException(e);
        }
    }

    private Query buscarNamedQuery(String namedQuery, Class classe, Object... var) throws CondoException {
        Query query = getEntityManager().createNamedQuery(namedQuery, classe);
        if (var.length > 0) {
            int i = 0;
            for (Object v : var) {
                query.setParameter("p" + i++, v);
            }
        }
        try {
            return query;
        } catch (Exception e) {
            throw new CondoDAOException(e);
        }
    }

}
