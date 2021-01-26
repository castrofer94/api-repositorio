package org.condo.api.cadastro.resources;

import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.condo.api.cadastro.as.UsuarioAS;
import org.condo.api.cadastro.entidade.Usuario;
import org.condo.api.cadastro.entidade.dto.ResponseModel;
import org.condo.api.cadastro.entidade.dto.StatusResponse;
import org.condo.api.cadastro.exception.CondoException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("usuario")
@Api(value = "Usuarios", protocols = "http", tags = "Usuarios")
@RequestScoped
public class UsuarioResource {

    private final Logger log = LogManager.getLogger(getClass());

    @EJB
    private UsuarioAS as;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Cadastra novo usuario",
            notes = "Cadastra um novo usuario no sistema",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Inclusão!", response = ResponseModel.class)
    })
    public ResponseModel<Usuario> novoUsuario(@ApiParam(required = true) Usuario usuario) {
        ResponseModel<Usuario> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().add(as.novoUsuario(usuario));
        } catch (CondoException e) {
            log.error(e, e);
            responseModel.setStatus(new StatusResponse(e.getCodigo().longValue(), e.getDescricao()));
        } catch (Exception e) {
            log.error(e, e);
            responseModel.setStatus(new StatusResponse(500L, e.getMessage() != null ? e.getMessage() : e.getCause().getMessage()));
        }
        return responseModel;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Lista usuarios",
            notes = "Lista todos os usuarios do sistema",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Listagem!", response = ResponseModel.class)
    })
    public ResponseModel<Usuario> listaTodosUsuarios() {
        ResponseModel<Usuario> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().addAll(as.listaTodosUsuarios());
        } catch (CondoException e) {
            e.printStackTrace();
            responseModel.setStatus(new StatusResponse(e.getCodigo().longValue(), e.getDescricao()));
        } catch (Exception e) {
            e.printStackTrace();
            responseModel.setStatus(new StatusResponse(500L, "Falha ao realizar busca"));
        }
        return responseModel;
    }

    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Lista usuario por email",
            notes = "Lista usuario pr email",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Listagem!", response = ResponseModel.class)
    })
    public ResponseModel<Usuario> usuarioPorEmail(@PathParam("email") String email) {
        ResponseModel<Usuario> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().add(as.usuarioPorEmail(email));
        } catch (CondoException e) {
            e.printStackTrace();
            responseModel.setStatus(new StatusResponse(e.getCodigo().longValue(), e.getDescricao()));
        } catch (Exception e) {
            e.printStackTrace();
            responseModel.setStatus(new StatusResponse(500L, "Falha ao realizar busca"));
        }
        return responseModel;
    }

    @GET
    @Path("verificaDisponibilidade/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Verifica a disponibilidade do email em questão",
            notes = "Verifica disponibilidade email",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Listagem!", response = ResponseModel.class)
    })
    public ResponseModel<Boolean> verificaDisponibilidadeEmail(@PathParam("email") String email) {
        ResponseModel<Boolean> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().add(as.verificaDisponibilidadeEmail(email));
        } catch (CondoException e) {
            e.printStackTrace();
            responseModel.setStatus(new StatusResponse(e.getCodigo().longValue(), e.getDescricao()));
        } catch (Exception e) {
            e.printStackTrace();
            responseModel.setStatus(new StatusResponse(500L, "Falha ao realizar busca"));
        }
        return responseModel;
    }
}
