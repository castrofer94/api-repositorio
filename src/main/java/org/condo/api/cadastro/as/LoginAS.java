package org.condo.api.cadastro.as;

import com.google.common.hash.Hashing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.condo.api.cadastro.entidade.Usuario;
import org.condo.api.cadastro.exception.CondoDAOException;
import org.condo.api.cadastro.exception.CondoException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@LocalBean
@Stateless
public class LoginAS {

    private final Logger log = LogManager.getLogger(getClass());

    @EJB
    private UsuarioAS usuarioAS;

    public Usuario login(String chave) throws CondoException {
        String[] chave64 = new String(Base64.getDecoder().decode(chave)).split("&");
        String email = chave64[0];
        String senha = chave64[1];
        try {
            Usuario usuario = usuarioAS.usuarioPorEmail(email);
            if (toSha256(usuario.getSenha()).equals(senha)) {
                return usuario;
            } else {
                throw new CondoException(401, "Senha não combina com o email do usuario");
            }
        } catch (CondoDAOException e) {
            if (e.getCodigo() == 1001) {
                throw new CondoException(402, "Email não encontrado, cadastre-se para poder acessar");
            } else {
                throw e;
            }
        }
    }

    private String toSha256(String txt) {
        return Hashing.sha256()
                .hashString(txt, StandardCharsets.UTF_8)
                .toString();
    }
}
