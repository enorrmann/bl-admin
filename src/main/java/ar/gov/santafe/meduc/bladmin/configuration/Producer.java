package ar.gov.santafe.meduc.bladmin.configuration;

import ar.gov.santafe.simpledb.SimpleDbAccess;
import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.sql.DataSource;

public class Producer {

    @Resource(name = "jdbc/bladmin")
    private DataSource dataSource;

    @Singleton
    @Produces
    public SimpleDbAccess getSimpleDb() {
        return new SimpleDbAccess(dataSource);
    }
}
