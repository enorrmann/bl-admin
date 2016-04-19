package ar.gov.santafe.meduc.bladmin.configuration;

import ar.gov.santafe.meduc.serviceLocator.CustomJsonProvider;
import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    public static final String fakelogicUrl = "http://localhost:3000/";

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();

        resources.add(CustomJsonProvider.class);
        addRestResourceClasses(resources);
        resources.add(ar.gov.santafe.meduc.bladmin.impl.CondicionServiceImpl.class);
        resources.add(ar.gov.santafe.meduc.bladmin.impl.CasoDeUsoServiceImpl.class);
        resources.add(ar.gov.santafe.meduc.bladmin.impl.RequerimientoServiceImpl.class);
        resources.add(ar.gov.santafe.meduc.bladmin.impl.TicketServiceImpl.class);
        
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
