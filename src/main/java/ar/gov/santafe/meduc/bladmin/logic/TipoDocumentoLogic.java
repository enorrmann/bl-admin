package ar.gov.santafe.meduc.bladmin.logic;

import ar.gov.santafe.meduc.bladmin.dao.TipoDocumentoDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.dto.SimpleDtoUtils;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author enorrmann
 */
public class TipoDocumentoLogic {

    @Inject
    private SimpleDbAccess db;

    public List<SimpleDto> listAll() {
        TipoDocumentoDao cd = db.getDao(TipoDocumentoDao.class);
        List<SimpleDto> all = cd.all();
        for (SimpleDto unSimpleDto : all) {
            SimpleDtoUtils.replaceForArray(unSimpleDto, "temas");
        }
        return all;
    }

    public SimpleDto findById(Long id) {
        return db.getDao(TipoDocumentoDao.class).findById(id);
    }

    public SimpleDto create(SimpleDto simpleDto) {
        TipoDocumentoDao cd = db.getDao(TipoDocumentoDao.class);
        Long id = cd.getNewId();
        simpleDto.add("id_documento", id);
        SimpleDtoUtils.replaceForString(simpleDto, "temas");
        cd.insert(simpleDto);
        return simpleDto;
    }

    public SimpleDto update(SimpleDto simpleDto) {
        Long id = simpleDto.getLong("id_documento");
        db.update(simpleDto, TipoDocumentoDao.TABLE_NAME);

        return simpleDto;
    }

    public void delete(Long id) {
        TipoDocumentoDao cd = db.getDao(TipoDocumentoDao.class);
        cd.delete(id);
    }

    public List<SimpleDto> search(SimpleDto filterDto) {

        return listAll();
    }
}
