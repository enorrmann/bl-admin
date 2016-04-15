package ar.gov.santafe.meduc.bladmin.impl;

import ar.gov.santafe.meduc.bladmin.configuration.ApplicationConfig;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.CasoDeUsoService;
import ar.gov.santafe.meduc.interfaces.GenericCrudService;
import static ar.gov.santafe.meduc.serviceLocator.ServiceLocator.getService;
import java.util.List;

/**
 *
 * @author enorrmann
 */
public class CasoDeUsoServiceImpl implements CasoDeUsoService {

    CasoDeUsoService rsDao = getService(CasoDeUsoService.class, ApplicationConfig.fakelogicUrl);

    @Override
    public List<SimpleDto> all() {
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