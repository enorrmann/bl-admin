package ar.gov.santafe.meduc.bladmin.logic;

import ar.gov.santafe.meduc.bladmin.dao.RequerimientoDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author enorrmann
 */
public class RequerimientoLogic {

    @Inject
    private SimpleDbAccess db;

    public RequerimientoLogic() {

    }

    public List<SimpleDto> listAll() {
        RequerimientoDao dao = db.getDao(RequerimientoDao.class);
        List<SimpleDto> all = dao.all();
        return all;
    }

    public SimpleDto findById(Long id) {
        RequerimientoDao dao = db.getDao(RequerimientoDao.class);
        SimpleDto dto = dao.findById(id);
        dto.add("casos_de_uso", dao.getCasos(id));
        return dto;
    }

}
