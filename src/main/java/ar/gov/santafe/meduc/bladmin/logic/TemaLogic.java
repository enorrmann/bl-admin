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

}
