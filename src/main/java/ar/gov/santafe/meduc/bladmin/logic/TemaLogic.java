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

    @Inject
    private TemaDao temaDao;

    public TemaLogic() {

    }

    public List<SimpleDto> listAll() {
        return temaDao.all();
    }

    public SimpleDto findById(Long id) {
        return temaDao.findById(id);
    }

    public SimpleDto insert(SimpleDto simpleDto) {
        Long id = temaDao.getNewId();
        simpleDto.add("id_tema", id);
        temaDao.insert(simpleDto);
        return simpleDto;
    }

    public void delete(Long id) {
        temaDao.delete(id);
    }

    public SimpleDto update(SimpleDto simpleDto) {
        return db.update(simpleDto, TemaDao.TABLE_NAME);
    }

    public List<SimpleDto> findByIdIn(String id) {
        return temaDao.findByIdIn(id);
    }

}
