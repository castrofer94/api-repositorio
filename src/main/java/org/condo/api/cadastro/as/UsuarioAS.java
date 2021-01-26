package org.condo.api.cadastro.as;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.condo.api.cadastro.entidade.Usuario;
import org.condo.api.cadastro.entidade.dao.GenericDao;
import org.condo.api.cadastro.exception.CondoDAOException;
import org.condo.api.cadastro.exception.CondoException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class UsuarioAS {

    private final Logger log = LogManager.getLogger(getClass());

    @EJB
    private GenericDao dao;

    public Usuario novoUsuario(Usuario usuario) throws CondoException {
        log.info("Criando novo usuário");
        if (usuario.getId() != null) {
            dao.atualizar(usuario);
        } else {
            dao.salvar(usuario);
        }
        return usuario;
    }

    public List<Usuario> listaTodosUsuarios() throws CondoException {
        log.info("Listando todos os usuarios do sistema");
        return dao.getList(Usuario.class);
    }

    public Usuario usuarioPorEmail(String email) throws CondoException {
        log.info("Usuario por email");
        return (Usuario) dao.buscarEntidadeNamedQuery("Busca.porEmail", Usuario.class, email);
    }

    public boolean verificaDisponibilidadeEmail(String email) throws CondoException {
        try {
            log.info("Verificando disponibilidade do email " + email);
            return usuarioPorEmail(email) == null;
        } catch (CondoDAOException e) {
            if (e.getCodigo() == 1001) {
                return true;
            } else {
                throw new CondoException(e);
            }
        }
    }
}
