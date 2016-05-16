package ar.gov.santafe.meduc.bladmin.service.impl;

import ar.gov.santafe.meduc.bladmin.logic.DocumentoLogic;
import ar.gov.santafe.meduc.bladmin.logic.TipoDocumentoLogic;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.TipoDocumentoService;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author enorrmann
 */
@Named
@Stateless
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    @Inject
    private TipoDocumentoLogic logic;

    @Context
    private UriInfo uriInfo;

    @Override
    public List<SimpleDto> searchBy(SimpleDto filter) {
        return logic.search(filter);
    }

    @Override
    public List<SimpleDto> all() {
        return logic.search(getFilters());
    }

    @Override
    public SimpleDto findById(String id) {
        return logic.findById(Long.valueOf(id));
    }

    @Override
    public SimpleDto create(SimpleDto simpleDto) {
        return logic.create(simpleDto);
    }

    @Override
    public SimpleDto update(String id, SimpleDto simpleDto) {
        return logic.update(simpleDto);
    }

    @Override
    public SimpleDto delete(String id) {
        logic.delete(Long.valueOf(id));
        return null;
    }

    private SimpleDto getFilters() {
        String filters = uriInfo.getQueryParameters().getFirst("_filters");
        if (filters != null) {
            return new SimpleDto(filters);
        } 
        return new SimpleDto();
    }

}
