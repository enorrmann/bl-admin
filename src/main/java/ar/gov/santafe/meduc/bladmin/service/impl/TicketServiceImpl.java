package ar.gov.santafe.meduc.bladmin.service.impl;

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

    @Override
    public List<SimpleDto> all() {
        String query = "select * from ( select ID_TICKET as id, titulo from AD_MA_TICKET  order by ID_TICKET desc ) where rownum < 50";
        List<SimpleDto> resultList = db.select(query);
        return resultList;
    }

    @Override
    public SimpleDto findById(String id) {
        return db.findById(id, "AD_MA_TICKET");
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
