package org.condo.api.cadastro.resources;

import io.swagger.annotations.*;
import org.condo.api.cadastro.as.CategoriaVendaAS;
import org.condo.api.cadastro.entidade.CategoriaVenda;
import org.condo.api.cadastro.entidade.dto.ResponseModel;
import org.condo.api.cadastro.entidade.dto.StatusResponse;
import org.condo.api.cadastro.exception.CondoException;
import org.postgresql.util.PSQLException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("categoriaVenda")
@Api(value = "Categorias de venda", protocols = "http", tags = "Categorias de venda")
@RequestScoped
public class CategoriaVendaResource {

    @EJB
    private CategoriaVendaAS as;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Cadastra nova categoria de venda",
            notes = "Cadastra um nova categoria de venda no sistema",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Inclusão!", response = ResponseModel.class)
    })
    public ResponseModel<CategoriaVenda> novoCategoriaVenda(@ApiParam(required = true) CategoriaVenda categoriaVenda) {
        ResponseModel<CategoriaVenda> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().add(as.novoCategoriaVenda(categoriaVenda));
        } catch (Exception e) {
            responseModel.setStatus(new StatusResponse(500L, "Falha ao salvar categoria de Venda"));
        }
        return responseModel;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Lista categoriaVendas",
            notes = "Lista todos os categoriaVendas do sistema",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Listagem!", response = ResponseModel.class)
    })
    public ResponseModel<CategoriaVenda> listaTodosCategoriaVendas() {
        ResponseModel<CategoriaVenda> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().addAll(as.listaTodasCategoriaVenda());
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
