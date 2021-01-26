package org.condo.api.cadastro.resources;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("v1")
public class ApplicationConfig extends Application {

    private final Logger log;

    public ApplicationConfig() {
        this.log = LogManager.getLogger(getClass());
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("API-CADASTRO");
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("/api-cadastro/v1");
        beanConfig.setResourcePackage("org.condo.api.cadastro.resources");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        try {
            Class jsonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
            resources.add(jsonProvider);
        } catch (ClassNotFoundException e) {
            log.trace("Erro ao adicionar o provider do Json", e);
        }
        addMyRestResourceClasses(resources);
        addRestResourceClasses(resources);
        return resources;
    }

    private void addMyRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(UsuarioResource.class);
        resources.add(TipoProdutoResource.class);
        resources.add(ProdutoVendaResource.class);
        resources.add(CondominioResource.class);
        resources.add(CategoriaVendaResource.class);
        resources.add(LoginResource.class);
    }
}
