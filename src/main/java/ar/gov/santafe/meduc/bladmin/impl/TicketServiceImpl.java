package ar.gov.santafe.meduc.bladmin.impl;

import ar.gov.santafe.meduc.bladmin.util.DbHelper;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.TicketService;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author enorrmann
 */
@Stateless
public class TicketServiceImpl implements TicketService {

    @Resource(name = "jdbc/bladmin")
    private DataSource dataSource;

    @Override
    public List<SimpleDto> all() {
        String query = "select * from ( select ID_TICKET as id, titulo from AD_MA_TICKET  order by ID_TICKET desc ) where rownum < 50";
        DbHelper db = new DbHelper(dataSource);
        List<SimpleDto> resultList = db.select(query);
        return resultList;
    }

    @Override
    public SimpleDto findById(String id) {
        String query = "select ID_TICKET as id,titulo,consulta,respuesta from AD_MA_TICKET where ID_TICKET = ?";
        DbHelper db = new DbHelper(dataSource);
        Long idLong = Long.valueOf(id);

        SimpleDto simpleDto = db.selectById(query, idLong);
        return simpleDto;
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
