package ar.gov.santafe.meduc.bladmin.impl;

import ar.gov.santafe.meduc.bladmin.configuration.ApplicationConfig;
import ar.gov.santafe.meduc.bladmin.dao.CasoDeUsoDao;
import ar.gov.santafe.meduc.bladmin.dao.service.DaoService;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.CasoDeUsoService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import static ar.gov.santafe.meduc.serviceLocator.ServiceLocator.getService;

/**
 *
 * @author enorrmann
 */
@Named
@Stateless
public class CasoDeUsoServiceImpl implements CasoDeUsoService {

    CasoDeUsoService rsDao = getService(CasoDeUsoService.class, ApplicationConfig.fakelogicUrl);

    @Inject
    private DaoService daoService;

    @Override
    public List<SimpleDto> all() {
         return daoService.forDao(CasoDeUsoDao.class).all();
    }

    @Override
    public SimpleDto findById(String id) {
        return daoService.forDao(CasoDeUsoDao.class).findById(Long.valueOf(id));
    }

    @Override
    public SimpleDto create(SimpleDto simpleDto) {
        return new SimpleDto().add("inserted", daoService.forDao(CasoDeUsoDao.class).insert(simpleDto));
    }

    @Override
    public SimpleDto update(String id, SimpleDto simpleDto) {
        return new SimpleDto().add("updated", daoService.forDao(CasoDeUsoDao.class).update(simpleDto));
    }

    @Override
    public SimpleDto delete(String id) {
        return rsDao.delete(id);
    }

    @Override
    public List<SimpleDto> searchBy(SimpleDto filter) {
        List<SimpleDto> allCasos = rsDao.all();
        List<SimpleDto> matchCasos = new ArrayList<>();
        for (SimpleDto unCaso : allCasos) {
            if (unCaso.getList("requerimientos").contains(filter.getLong("id"))) {
                matchCasos.add(unCaso);
            }
        }
        return matchCasos;
    }

}
