package org.condo.api.cadastro.resources;

import io.swagger.annotations.*;
import org.condo.api.cadastro.as.ProdutoVendaAS;
import org.condo.api.cadastro.entidade.ProdutoVenda;
import org.condo.api.cadastro.entidade.dto.ResponseModel;
import org.condo.api.cadastro.entidade.dto.StatusResponse;
import org.condo.api.cadastro.exception.CondoException;
import org.postgresql.util.PSQLException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("produtoVenda")
@Api(value = "Produtos de venda", protocols = "http", tags = "Produtos de venda")
@RequestScoped
public class ProdutoVendaResource {

    @EJB
    private ProdutoVendaAS as;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Cadastra novo produto de venda",
            notes = "Cadastra um novo produto de venda no sistema",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Inclusão!", response = ResponseModel.class)
    })
    public ResponseModel<ProdutoVenda> novoProdutoVenda(@ApiParam(required = true) ProdutoVenda produtoVenda) {
        ResponseModel<ProdutoVenda> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().add(as.novoProdutoVenda(produtoVenda));
        } catch (Exception e) {
            responseModel.setStatus(new StatusResponse(500L, "Falha ao salvar produto de venda"));
        }
        return responseModel;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Lista produtos de venda",
            notes = "Lista todos os produtos de venda do sistema",
            response = ResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso!", response = ResponseModel.class)
            ,
            @ApiResponse(code = 500, message = "Problemas na Listagem!", response = ResponseModel.class)
    })
    public ResponseModel<ProdutoVenda> listaTodosProdutoVendas() {
        ResponseModel<ProdutoVenda> responseModel = new ResponseModel<>();
        try {
            responseModel.setStatus(new StatusResponse(200L, "Ok"));
            responseModel.getData().addAll(as.listaTodosProdutoVenda());
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
