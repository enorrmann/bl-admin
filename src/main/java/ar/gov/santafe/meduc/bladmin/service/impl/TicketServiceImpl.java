package ar.gov.santafe.meduc.bladmin.service.impl;

import ar.gov.santafe.meduc.bladmin.dao.TicketDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.TicketService;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author enorrmann
 */
@Stateless
public class TicketServiceImpl implements TicketService {

    @Inject
    private SimpleDbAccess db;

    @Inject
    private TicketDao dao;

    @Override
    public List<SimpleDto> all() {
        return dao.all();
    }

    @Override
    public SimpleDto findById(String id) {
        return dao.findById(Long.valueOf(id));
    }

    @Override
    public SimpleDto create(SimpleDto simpleDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SimpleDto update(String id, SimpleDto simpleDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SimpleDto delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
