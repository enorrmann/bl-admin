package ar.gov.santafe.meduc.bladmin.logic;

import ar.gov.santafe.meduc.bladmin.dao.TemaDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author enorrmann
 */
public class TemaLogic {

    @Inject
    private SimpleDbAccess db;

    public TemaLogic() {

    }

    public List<SimpleDto> listAll() {
        TemaDao dao = db.getDao(TemaDao.class);
        List<SimpleDto> all = dao.all();
        return all;
    }

    public SimpleDto findById(Long id) {
        TemaDao dao = db.getDao(TemaDao.class);
        SimpleDto dto = dao.findById(id);
        return dto;
    }

    public SimpleDto insert(SimpleDto simpleDto) {
        TemaDao dao = db.getDao(TemaDao.class);
        Long id = dao.getNewId();
        simpleDto.add("id_tema", id);
        dao.insert(simpleDto);
        return simpleDto;
    }

    public void delete(Long id) {
        TemaDao dao = db.getDao(TemaDao.class);
        dao.delete(id);
    }

    public SimpleDto update(SimpleDto simpleDto) {
        return db.update(simpleDto, TemaDao.TABLE_NAME);
    }

    public List<SimpleDto> findByIdIn(String id) {
        TemaDao dao = db.getDao(TemaDao.class);
        return dao.findByIdIn(id);
    }

}
