package ar.gov.santafe.meduc.bladmin.dao.service;

import ar.gov.santafe.meduc.bladmin.dao.CasoDeUsoDao;
import ar.gov.santafe.meduc.bladmin.dao.config.GenericDao;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.sql.DataSource;

@Named
public class DaoService {

    @Resource(name = "jdbc/bladmin")
    private DataSource dataSource;
    DaoFactory df = DaoFactory.instance(dataSource);

    public GenericDao forDao(Class<CasoDeUsoDao> aClass) {
        return df.forDao(aClass);
    }
    


}
