package ar.gov.santafe.meduc.bladmin.logic;

import ar.gov.santafe.meduc.bladmin.dao.RequerimientoDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.ArrayList;
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
        return db.getDao(RequerimientoDao.class).all();
    }

    public SimpleDto findById(Long id) {
        RequerimientoDao dao = db.getDao(RequerimientoDao.class);
        SimpleDto dto = dao.findById(id);
        dto.add("documentos", dao.getCasos(id));
        return dto;
    }

    public List<SimpleDto> search(SimpleDto filterDto) {
        Long idRequerimiento = filterDto.getLong("id_ma_requerimiento");
        Long idCaso = filterDto.getLong("id_documento");
        if (idCaso != null) {
            return db.getDao(RequerimientoDao.class).findByIdCaso(idCaso);
        } else if (idRequerimiento != null) {
            List<SimpleDto>  resultList = new ArrayList<>();
            resultList.add(db.getDao(RequerimientoDao.class).findById(idRequerimiento));
            return resultList;
        }
        return listAll();
    }

    public SimpleDto update(SimpleDto simpleDto) {
        Long id = simpleDto.getLong("id_ma_requerimiento");
        RequerimientoDao dao = db.getDao(RequerimientoDao.class);
        dao.deleteRequerimientoXCaso(id);
        dao.insertRequerimientoXCaso(id, simpleDto.getLongList("documentos"));
        return simpleDto;
    }

}
