package org.condo.api.cadastro.resources;

import io.swagger.annotations.*;
import org.condo.api.cadastro.entidade.TipoProduto;
import org.condo.api.cadastro.as.TipoProdutoAS;
import org.condo.api.cadastro.entidade.dto.ResponseModel;
import org.condo.api.cadastro.entidade.dto.StatusResponse;
import org.condo.api.cadastro.exception.CondoException;
import org.postgresql.util.PSQLException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("tipoProduto")
@Api(value = "Tipos de produto", protocols = "http", tags = "Tipos de produto")
@RequestScoped
public class TipoProdutoResource {

    @EJB
    private TipoProdutoAS as;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Cadastra novo tipo de produto",
            notes = "Cadastra um novo tipo de produto no sistema",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Inclusão!", response = ResponseModel.class)
    })
    public ResponseModel<TipoProduto> novoTipoProduto(@ApiParam(required = true) TipoProduto tipoProduto) {
        ResponseModel<TipoProduto> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().add(as.novoTipoProduto(tipoProduto));
        } catch (Exception e) {
            responseModel.setStatus(new StatusResponse(500L, "Falha ao salvar tipo de produto"));
        }
        return responseModel;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Lista tipos de produtos",
            notes = "Lista todos os tipos de produtos do sistema",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Listagem!", response = ResponseModel.class)
    })
    public ResponseModel<TipoProduto> listaTodosTipoProdutos() {
        ResponseModel<TipoProduto> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().addAll(as.listaTodosTiposProdutos());
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
