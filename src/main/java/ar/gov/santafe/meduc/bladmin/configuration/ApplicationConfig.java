package ar.gov.santafe.meduc.bladmin.configuration;

import ar.gov.santafe.meduc.bladmin.service.impl.TipoDocumentoServiceImpl;
import ar.gov.santafe.meduc.serviceLocator.CustomJsonProvider;
import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    public static final String fakelogicUrl = "http://localhost:3000/";
    public static final String realUrl = "http://localhost:8080/bl-admin/api/";

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();

        resources.add(CustomJsonProvider.class);
        addRestResourceClasses(resources);
        resources.add(ar.gov.santafe.meduc.bladmin.service.impl.DocumentoServiceImpl.class);
        resources.add(ar.gov.santafe.meduc.bladmin.service.impl.TipoDocumentoServiceImpl.class);
        resources.add(ar.gov.santafe.meduc.bladmin.service.impl.RequerimientoServiceImpl.class);
        resources.add(ar.gov.santafe.meduc.bladmin.service.impl.TicketServiceImpl.class);
        resources.add(ar.gov.santafe.meduc.bladmin.service.impl.TemaServiceImpl.class);
        
        return resources;
    }
    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
    }



}
