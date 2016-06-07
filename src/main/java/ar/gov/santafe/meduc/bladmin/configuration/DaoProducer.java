package ar.gov.santafe.meduc.bladmin.configuration;

import ar.gov.santafe.meduc.bladmin.dao.DocumentoDao;
import ar.gov.santafe.meduc.bladmin.dao.RequerimientoDao;
import ar.gov.santafe.meduc.bladmin.dao.TemaDao;
import ar.gov.santafe.meduc.bladmin.dao.TicketDao;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

public class DaoProducer {

    @Inject
    private SimpleDbAccess db;

    @Singleton
    @Produces
    public DocumentoDao getDocumentoDao() {
        return db.getDao(DocumentoDao.class);
    }

    @Singleton
    @Produces
    public TemaDao getTemaDao() {
        return db.getDao(TemaDao.class);
    }

    @Singleton
    @Produces
    public TicketDao getTicketDao() {
        return db.getDao(TicketDao.class);
    }

    @Singleton
    @Produces
    public RequerimientoDao getRequerimientoDao() {
        return db.getDao(RequerimientoDao.class);
    }
}
