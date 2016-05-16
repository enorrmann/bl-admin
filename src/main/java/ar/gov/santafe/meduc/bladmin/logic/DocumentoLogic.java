package ar.gov.santafe.meduc.bladmin.logic;

import ar.gov.santafe.meduc.bladmin.dao.DocumentoDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.dto.SimpleDtoUtils;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author enorrmann
 */
public class DocumentoLogic {

    @Inject
    private SimpleDbAccess db;

    public List<SimpleDto> listAll() {
        DocumentoDao cd = db.getDao(DocumentoDao.class);
        List<SimpleDto> all = cd.all();
        for (SimpleDto unSimpleDto : all) {
            SimpleDtoUtils.replaceForArray(unSimpleDto, "temas");
        }
        return all;
    }

    public SimpleDto findById(Long id) {
        DocumentoDao cd = db.getDao(DocumentoDao.class);
        SimpleDto dto = cd.findById(id);
        SimpleDtoUtils.replaceForArray(dto, "temas");
        /*dto.add("precondiciones", cd.getPrecondiciones(id));
        dto.add("postcondiciones", cd.getPostcondiciones(id));*/
        dto.add("requerimientos", cd.getRequerimientos(id));
        return dto;
    }

    public SimpleDto create(SimpleDto simpleDto) {
        DocumentoDao cd = db.getDao(DocumentoDao.class);
        Long id = cd.getNewId();
        simpleDto.add("id_documento", id);
        SimpleDtoUtils.replaceForString(simpleDto, "temas");
        cd.insert(simpleDto);

        /*cd.insertPrecondiciones(id, simpleDto.getLongList("precondiciones"));
        cd.insertPostcondiciones(id, simpleDto.getLongList("postcondiciones"));*/
        cd.insertRequerimientos(id, simpleDto.getLongList("requerimientos"));
        return simpleDto;
    }

    public SimpleDto update(SimpleDto simpleDto) {
/*        DocumentoDao cd = db.getDao(DocumentoDao.class);
        Long id = simpleDto.getLong("id_documento");
        cd.deletePostcondiciones(id);
        cd.deletePrecondiciones(id);*/
        SimpleDtoUtils.replaceForString(simpleDto, "temas");
        /*cd.insertPrecondiciones(id, simpleDto.getLongList("precondiciones"));
        cd.insertPostcondiciones(id, simpleDto.getLongList("postcondiciones"));*/
        db.update(simpleDto, DocumentoDao.TABLE_NAME);

        return simpleDto;
    }

    public void delete(Long id) {
        DocumentoDao cd = db.getDao(DocumentoDao.class);
        /*cd.deletePostcondiciones(id);
        cd.deletePrecondiciones(id);*/
        cd.deleteTemas(id);
        cd.deleteRequerimientos(id);
        cd.delete(id);

    }

    public List<SimpleDto> search(SimpleDto filterDto) {
        Long idTema = filterDto.getLong("id_tema");
        Long idTipoDocumento = filterDto.getLong("id_tipo_documento");
        if (idTema != null || idTipoDocumento != null) {
            DocumentoDao cd = db.getDao(DocumentoDao.class);
            List<SimpleDto> filtered = null;
            if (idTema != null && idTipoDocumento != null) {
                filtered = cd.findByIdTemaAndTipoDocumento(idTema, idTipoDocumento);
            } else if (idTema != null) {
                filtered = cd.findByIdTema(idTema);
            } else {
                filtered = cd.findByIdTipoDocumento(idTipoDocumento);
            }

            for (SimpleDto unSimpleDto : filtered) {
                SimpleDtoUtils.replaceForArray(unSimpleDto, "temas");
            }
            return filtered;

        }
        return listAll();
    }
}
