package ar.gov.santafe.meduc.bladmin.logic;

import ar.gov.santafe.meduc.bladmin.dao.CondicionDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.List;
import javax.inject.Inject;
import org.skife.jdbi.v2.Handle;

/**
 *
 * @author enorrmann
 */
public class CondicionLogic {

    @Inject
    private SimpleDbAccess db;

    final String condicionTable = "BL_CONDICION";
    final String condicionDependeTable = "BL_CONDICION_DEPENDE";

    public CondicionLogic() {

    }

    public SimpleDto update(SimpleDto simpleDto) {
        Long idCondicion = simpleDto.getLong("id");
        List<Long> idCondicionDepende = simpleDto.getLongList("depende_de");
        simpleDto.remove("depende_de");
        updateDependencias(idCondicion, idCondicionDepende);
        return db.update(simpleDto, condicionTable);
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

    public SimpleDto update(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
