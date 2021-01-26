package org.condo.api.cadastro.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.condo.api.cadastro.as.LoginAS;
import org.condo.api.cadastro.entidade.Usuario;
import org.condo.api.cadastro.entidade.dto.ResponseModel;
import org.condo.api.cadastro.entidade.dto.StatusResponse;
import org.condo.api.cadastro.exception.CondoException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("login")
@Api(value = "Login", protocols = "http", tags = "Login")
@RequestScoped
public class LoginResource {

    @EJB
    private LoginAS as;

    @GET
    @Path("{login}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Logar no sistema",
            notes = "Validando dados do usuário que pretende logar",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Listagem!", response = ResponseModel.class)
    })
    public ResponseModel<Usuario> listaTodosUsuarios(@PathParam("login") String login) {
        ResponseModel<Usuario> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().add(as.login(login));
        } catch (CondoException e) {
            responseModel.setStatus(new StatusResponse(e.getCodigo().longValue(), e.getDescricao()));
        } catch (Exception e) {
            responseModel.setStatus(new StatusResponse(500L, "Falha ao realizar busca"));
        }
        return responseModel;
    }
}
