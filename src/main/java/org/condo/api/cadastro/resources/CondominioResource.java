package org.condo.api.cadastro.resources;

import io.swagger.annotations.*;
import org.condo.api.cadastro.as.CondominioAS;
import org.condo.api.cadastro.entidade.Condominio;
import org.condo.api.cadastro.entidade.dto.ResponseModel;
import org.condo.api.cadastro.entidade.dto.StatusResponse;
import org.condo.api.cadastro.exception.CondoException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("condominio")
@Api(value = "Condominios", protocols = "http", tags = "Condominios")
@RequestScoped
public class CondominioResource {

    @EJB
    private CondominioAS as;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Cadastra novo condominio",
            notes = "Cadastra um novo condominio no sistema",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Inclusão!", response = ResponseModel.class)
    })
    public ResponseModel<Condominio> novoCondominio(@ApiParam(required = true) Condominio condominio) {
        ResponseModel<Condominio> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().add(as.novoCondominio(condominio));
        } catch (Exception e) {
            responseModel.setStatus(new StatusResponse(500L, "Falha ao salvar condominio"));
        }
        return responseModel;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Lista condominios",
            notes = "Lista todos os condominios do sistema",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Listagem!", response = ResponseModel.class)
    })
    public ResponseModel<Condominio> listaTodosCondominios() {
        ResponseModel<Condominio> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().addAll(as.listaTodosCondominio());
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
