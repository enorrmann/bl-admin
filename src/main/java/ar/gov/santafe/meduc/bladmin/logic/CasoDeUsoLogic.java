package ar.gov.santafe.meduc.bladmin.logic;

import ar.gov.santafe.meduc.bladmin.dao.CasoDeUsoDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.List;
import javax.inject.Inject;
import org.skife.jdbi.v2.Handle;

/**
 *
 * @author enorrmann
 */
public class CasoDeUsoLogic {

    @Inject
    private SimpleDbAccess db;

    public CasoDeUsoLogic() {

    }

    public List<SimpleDto> listAll() {
        CasoDeUsoDao cd = db.getDao(CasoDeUsoDao.class);
        List<SimpleDto> all = cd.all();
        // no hacer esto, es muy pesado
        /*for (SimpleDto unSimpleDto : all) {
            unSimpleDto.add("temas", cd.getTemas(unSimpleDto.getLong("id")));
        }*/
        return all;
    }

    public SimpleDto findById(Long id) {
        CasoDeUsoDao cd = db.getDao(CasoDeUsoDao.class);
        SimpleDto dto = cd.findById(id);
        dto.add("temas", cd.getTemas(id));
        dto.add("precondiciones", cd.getPrecondiciones(id));
        dto.add("postcondiciones", cd.getPostcondiciones(id));
        dto.add("requerimientos", cd.getRequerimientos(id));
        return dto;
    }

    public SimpleDto create(SimpleDto simpleDto) {
        CasoDeUsoDao cd = db.getDao(CasoDeUsoDao.class);
        Long id = cd.getNewId();
        simpleDto.add("id", id);
        cd.insert(simpleDto);
        cd.insertTemas(id, simpleDto.getLongList("temas"));
        cd.insertPrecondiciones(id, simpleDto.getLongList("precondiciones"));
        cd.insertPostcondiciones(id, simpleDto.getLongList("postcondiciones"));
        cd.insertRequerimientos(id, simpleDto.getLongList("requerimientos"));
        return simpleDto;
    }

    public SimpleDto update(SimpleDto simpleDto) {
        CasoDeUsoDao cd = db.getDao(CasoDeUsoDao.class);
        Long id = simpleDto.getLong("id");
        cd.update(simpleDto);
        cd.deletePostcondiciones(id);
        cd.deletePrecondiciones(id);
        cd.deleteTemas(id);
        cd.deleteRequerimientos(id);
        cd.insertTemas(id, simpleDto.getLongList("temas"));
        cd.insertPrecondiciones(id, simpleDto.getLongList("precondiciones"));
        cd.insertPostcondiciones(id, simpleDto.getLongList("postcondiciones"));
        cd.insertRequerimientos(id, simpleDto.getLongList("requerimientos"));
        return simpleDto;
    }

    public SimpleDto delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<SimpleDto> search(SimpleDto filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
