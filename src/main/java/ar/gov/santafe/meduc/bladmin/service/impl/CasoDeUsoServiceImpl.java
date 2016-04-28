package ar.gov.santafe.meduc.bladmin.service.impl;

import ar.gov.santafe.meduc.bladmin.logic.CasoDeUsoLogic;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.CasoDeUsoService;
import ar.gov.santafe.simpledb.SimpleDbAccess;
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
public class CasoDeUsoServiceImpl implements CasoDeUsoService {

    @Inject
    private CasoDeUsoLogic logic;

    @Override
    public List<SimpleDto> searchBy(SimpleDto filter) {
        return logic.search(filter);
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
    public SimpleDto create(SimpleDto simpleDto) {
        return logic.create(simpleDto);
    }

    @Override
    public SimpleDto update(String id, SimpleDto simpleDto) {
        return logic.update(simpleDto);
    }

    @Override
    public SimpleDto delete(String id) {
        return logic.delete(Long.valueOf(id));
    }

}
