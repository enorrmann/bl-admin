package ar.gov.santafe.meduc.bladmin.logic;

import ar.gov.santafe.meduc.bladmin.dao.CondicionDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author enorrmann
 */
public class CondicionLogic {

    @Inject
    private SimpleDbAccess db;

    public CondicionLogic() {

    }

    public SimpleDto update(SimpleDto simpleDto) {
        Long idCondicion = simpleDto.getLong("id_condicion");
        List<Long> idCondicionDepende = simpleDto.getLongList("depende_de");
        updateDependencias(idCondicion, idCondicionDepende);
        db.update(simpleDto,CondicionDao.TABLE_NAME);
        return simpleDto;
    }

    public List<SimpleDto> listAll() {
        CondicionDao cd = db.getDao(CondicionDao.class);
        List<SimpleDto> all = cd.all();

        return all;
    }

    public SimpleDto findById(String id) {
        CondicionDao cd = db.getDao(CondicionDao.class);
        Long idCondicion = Long.valueOf(id);
        SimpleDto condicion = cd.findById(idCondicion);
        condicion.add("depende_de", getDependencias(idCondicion));
        return condicion;
    }

    private List<String> getDependencias(Long idCondicion) {
        CondicionDao cd = db.getDao(CondicionDao.class);
        List<String> dependencias = cd.getDependencias(idCondicion);
        return dependencias;
    }

    public SimpleDto create(SimpleDto simpleDto) {
        CondicionDao cd = db.getDao(CondicionDao.class);
        Long id = cd.getNewId();
        simpleDto.add("id", id);
        cd.insert(simpleDto);

        List<Long> idCondicionDepende = simpleDto.getLongList("depende_de");
        cd.insertDependencias(id, idCondicionDepende);
        return simpleDto;
    }

    private void updateDependencias(Long idCondicion, List<Long> idCondicionDepende) {
        CondicionDao cd = db.getDao(CondicionDao.class);
        cd.deleteDependencias(idCondicion);
        cd.insertDependencias(idCondicion, idCondicionDepende);
    }

    public void delete(Long idCondicion) {
        CondicionDao cd = db.getDao(CondicionDao.class);
        cd.deleteDependencias(idCondicion);
        cd.deleteCondicion(idCondicion);
    }

    public List<SimpleDto> search(SimpleDto filterDto) {
        List<SimpleDto> resultList = new ArrayList<>();
        Long perPage = filterDto.getLong("perPage");
        if (perPage!=null&&perPage.equals(0L)) {
            return resultList;
        }
        CondicionDao dao = db.getDao(CondicionDao.class);
        List<Long> ids = filterDto.getLongList("ids");
        if (ids != null && !ids.isEmpty()) {
            return dao.findByIdIn(ids);
        }
        Long idCondicion = filterDto.getLong("id_condicion");
        if (idCondicion != null) {
            SimpleDto dto = dao.findById(idCondicion);
            if (dto == null) {
                return resultList;
            }
            resultList.add(dto);
            return resultList;
        }
        return listAll();
    }

}
