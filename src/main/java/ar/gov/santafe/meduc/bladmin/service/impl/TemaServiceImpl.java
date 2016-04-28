package ar.gov.santafe.meduc.bladmin.service.impl;

import ar.gov.santafe.meduc.bladmin.logic.TemaLogic;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.TemaService;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author enorrmann
 */
@Named
@Stateless
public class TemaServiceImpl implements TemaService {

    @Inject
    private TemaLogic logic;

    @Override
    public SimpleDto create(SimpleDto simpleDto) {
        //logic.insert(simpleDto);
        return simpleDto;
    }

    @Override
    public SimpleDto update(String id, SimpleDto simpleDto) {
        //return logic.update(simpleDto);
        return simpleDto;
    }

    @Override
    public List<SimpleDto> all() {
        return logic.listAll();
    }

    @Override
    public SimpleDto findById(String id) {
        return logic.findById(Long.valueOf(id));
    }

    @Override
    public SimpleDto delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
