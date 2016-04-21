package ar.gov.santafe.meduc.bladmin.impl;

import ar.gov.santafe.meduc.bladmin.configuration.ApplicationConfig;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.CondicionService;
import static ar.gov.santafe.meduc.serviceLocator.ServiceLocator.getService;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author enorrmann
 */
public class CondicionServiceImpl implements CondicionService {

    CondicionService rsDao = getService(CondicionService.class, ApplicationConfig.fakelogicUrl);

    @Context
    UriInfo uriInfo;

    @Override
    public List<SimpleDto> all() {
        MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
        if (params != null && !params.isEmpty()) {
            if (params.get("_filters")!=null){
                String value = params.get("_filters").get(0);
            SimpleDto filtros = new SimpleDto(value);
            }
        }
        return rsDao.all();
    }

    @Override
    public SimpleDto findById(String id) {
        return rsDao.findById(id);
    }

    @Override
    public SimpleDto create(SimpleDto simpleDto) {
        return rsDao.create(simpleDto);
    }

    @Override
    public SimpleDto update(String id, SimpleDto simpleDto) {
        return rsDao.update(id, simpleDto);
    }

    @Override
    public SimpleDto delete(String id) {
        return rsDao.delete(id);
    }

}
