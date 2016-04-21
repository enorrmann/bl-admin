package ar.gov.santafe.meduc.bladmin.impl;

import ar.gov.santafe.meduc.bladmin.configuration.ApplicationConfig;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.TemaService;
import static ar.gov.santafe.meduc.serviceLocator.ServiceLocator.getService;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author enorrmann
 */
public class TemaServiceImpl implements TemaService {

    TemaService tsDao = getService(TemaService.class, ApplicationConfig.fakelogicUrl);

    @Resource(name = "jdbc/bladmin")
    private DataSource dataSource;

    @Override
    public List<SimpleDto> all() {
        return tsDao.all();
    }

    @Override
    public SimpleDto findById(String id) {
        return tsDao.findById(id);
    }

    @Override
    public SimpleDto create(SimpleDto simpleDto) {
        return tsDao.create(simpleDto);
    }

    @Override
    public SimpleDto update(String id, SimpleDto simpleDto) {
        return tsDao.update(id, simpleDto);
    }

    @Override
    public SimpleDto delete(String id) {
        return tsDao.delete(id);
    }

}
