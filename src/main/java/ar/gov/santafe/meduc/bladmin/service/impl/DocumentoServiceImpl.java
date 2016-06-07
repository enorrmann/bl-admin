package ar.gov.santafe.meduc.bladmin.service.impl;

import ar.gov.santafe.meduc.bladmin.configuration.WebLayer;
import ar.gov.santafe.meduc.bladmin.logic.DocumentoLogic;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.DocumentoService;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author enorrmann
 */
@Named
@Stateless
public class DocumentoServiceImpl implements DocumentoService {

    @Inject
    private DocumentoLogic logic;
    
    @Inject
    private WebLayer web;

    @Override
    public List<SimpleDto> all() {
        List<SimpleDto> result = logic.search(web.getQueryParams());
        return web.paginate(result);
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
}
