package ar.gov.santafe.meduc.bladmin.logic;

import ar.gov.santafe.meduc.bladmin.dao.RequerimientoDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author enorrmann
 */
public class RequerimientoLogic {

    @Inject
    private RequerimientoDao dao;

    public List<SimpleDto> listAll() {
        return dao.all();
    }

    public SimpleDto findById(Long id) {
        SimpleDto dto = dao.findById(id);
        dto.add("documentos", dao.getCasos(id));
        return dto;
    }

    public List<SimpleDto> search(SimpleDto filterDto) {
        String queryString = filterDto.getString("q");
        Long idRequerimiento = filterDto.getLong("id_ma_requerimiento");
        if (idRequerimiento == null && filterDto.getLong("q") != null) {
            idRequerimiento = filterDto.getLong("q");
        }
        Long idCaso = filterDto.getLong("id_documento");
        if (idCaso != null) {
            List<SimpleDto> resultList = dao.findByIdCaso(idCaso);
            return resultList != null ? resultList : new ArrayList<SimpleDto>();
        } else if (idRequerimiento != null) {
            List<SimpleDto> resultList = new ArrayList<>();
            SimpleDto result = dao.findById(idRequerimiento);
            if (result != null) {
                resultList.add(result);
            }
            return resultList;
        } else if (queryString != null && !queryString.trim().isEmpty()) {
            return dao.search("%" + queryString + "%");
        }

        return listAll();
    }

    public SimpleDto update(SimpleDto simpleDto) {
        Long id = simpleDto.getLong("id_ma_requerimiento");
        dao.deleteRequerimientoXCaso(id);
        dao.insertRequerimientoXCaso(id, simpleDto.getLongList("documentos"));
        return simpleDto;
    }

}
