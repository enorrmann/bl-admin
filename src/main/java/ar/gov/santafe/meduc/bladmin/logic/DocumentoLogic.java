package ar.gov.santafe.meduc.bladmin.logic;

import ar.gov.santafe.meduc.bladmin.dao.DocumentoDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.dto.SimpleDtoUtils;
import ar.gov.santafe.simpledb.SimpleDbAccess;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author enorrmann
 */
public class DocumentoLogic {

    @Inject
    private SimpleDbAccess db;

    @Inject
    private DocumentoDao documentoDao;

    public List<SimpleDto> listAll() {
        List<SimpleDto> all = documentoDao.all();
        for (SimpleDto unSimpleDto : all) {
            SimpleDtoUtils.replaceForArray(unSimpleDto, "temas");
        }
        return all;
    }

    public SimpleDto findById(Long id) {
        SimpleDto dto = documentoDao.findById(id);
        SimpleDtoUtils.replaceForArray(dto, "temas");
        dto.add("requerimientos", documentoDao.getRequerimientos(id));
        return dto;
    }

    public SimpleDto create(SimpleDto simpleDto) {
        Long id = documentoDao.getNewId();
        simpleDto.add("id_documento", id);
        SimpleDtoUtils.replaceForString(simpleDto, "temas");
        documentoDao.insert(simpleDto);
        documentoDao.insertRequerimientos(id, simpleDto.getLongList("requerimientos"));
        return simpleDto;
    }

    public SimpleDto update(SimpleDto simpleDto) {
        SimpleDtoUtils.replaceForString(simpleDto, "temas");
        db.update(simpleDto, DocumentoDao.TABLE_NAME);
        return simpleDto;
    }

    public void delete(Long id) {
        documentoDao.deleteTemas(id);
        documentoDao.deleteRequerimientos(id);
        documentoDao.delete(id);

    }

    public List<SimpleDto> search(SimpleDto filterDto) {
        String queryString = filterDto.getString("q");
        List<SimpleDto> filtered = new ArrayList<SimpleDto>();
        List<SimpleDto> toFilter = null;

        Long idTema = filterDto.getLong("id_tema");
        Long idTipoDocumento = filterDto.getLong("id_tipo_documento");
        if (idTema != null || idTipoDocumento != null) {
            if (idTema != null && idTipoDocumento != null) {
                toFilter = documentoDao.findByIdTemaAndTipoDocumento(idTema, idTipoDocumento);
            } else if (idTema != null) {
                toFilter = documentoDao.findByIdTema(idTema);
            } else {
                toFilter = documentoDao.findByIdTipoDocumento(idTipoDocumento);
            }

        } else {
            toFilter = listAll();
        }

        if (queryString != null && !queryString.isEmpty()) {
            for (SimpleDto unSimpleDto : toFilter) {
                if (SimpleDtoUtils.contains(unSimpleDto, queryString, "nombre","id_documento")) {
                    filtered.add(unSimpleDto);
                }
            }

        } else {
            filtered = toFilter;
        }

        for (SimpleDto unSimpleDto : filtered) {
            SimpleDtoUtils.replaceForArray(unSimpleDto, "temas");
        }

        return filtered;
    }

}
