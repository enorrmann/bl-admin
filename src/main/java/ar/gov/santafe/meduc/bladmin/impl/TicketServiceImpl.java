package ar.gov.santafe.meduc.bladmin.impl;

import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.TicketService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author enorrmann
 */
@Stateless
public class TicketServiceImpl implements TicketService{

    @PersistenceContext(unitName = "SigaeEJBPU")
    private EntityManager entityManager;

    @Override
    public List<SimpleDto> all() {
        Query query = entityManager.createNativeQuery("select ID_TICKET,titulo from AD_MA_TICKET where rownum < 50");
        List<Object[]> lista = query.getResultList();
        List resultList = new ArrayList();
        for (Object[] aRow : lista) {
            SimpleDto aDto = new SimpleDto()
                    .add("id", aRow[0])
                    .add("titulo", aRow[1]);
            resultList.add(aDto);
        }
        return resultList;
    }

    @Override
    public SimpleDto findById(String id) {
        Query query = entityManager.createNativeQuery("select ID_TICKET,titulo,consulta,respuesta from AD_MA_TICKET where ID_TICKET = :P_ID_TICKET");
        query.setParameter("P_ID_TICKET", Long.valueOf(id));
        List<Object[]> lista = query.getResultList();
        Object[] aRow = lista.get(0);
            SimpleDto aDto = new SimpleDto()
                    .add("id", aRow[0])
                    .add("titulo", aRow[1])
                    .add("consulta", aRow[2])
                    .add("respuesta", aRow[3]);
        return aDto;
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
