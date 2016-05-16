package ar.gov.santafe.meduc.bladmin.service.impl;

import ar.gov.santafe.meduc.bladmin.logic.RequerimientoLogic;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.RequerimientoService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author enorrmann
 */
@Stateless
public class RequerimientoServiceImpl implements RequerimientoService {

    @Context
    private UriInfo uriInfo;

    @Inject
    private RequerimientoLogic logic;

    @Override
    public List<SimpleDto> all() {
        String filters = uriInfo.getQueryParameters().getFirst("_filters");
        if (filters != null) {
            SimpleDto filterDto = new SimpleDto(filters);
            return logic.search(filterDto);
        }
        return logic.listAll();
    }

    @Override
    public SimpleDto findById(String id) {
        return logic.findById(Long.valueOf(id));
    }

    @Override
    public SimpleDto create(SimpleDto simpleDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SimpleDto update(String id, SimpleDto simpleDto) {
        return logic.update(simpleDto);
    }

    @Override
    public SimpleDto delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
