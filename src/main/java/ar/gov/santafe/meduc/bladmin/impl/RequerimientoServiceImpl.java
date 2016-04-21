package ar.gov.santafe.meduc.bladmin.impl;

import ar.gov.santafe.meduc.bladmin.configuration.ApplicationConfig;
import ar.gov.santafe.meduc.bladmin.util.DbHelper;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.meduc.interfaces.CasoDeUsoService;
import ar.gov.santafe.meduc.interfaces.RequerimientoService;
import static ar.gov.santafe.meduc.serviceLocator.ServiceLocator.getService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author enorrmann
 */
@Stateless
public class RequerimientoServiceImpl implements RequerimientoService {

    CasoDeUsoService cduDao = getService(CasoDeUsoService.class, ApplicationConfig.realUrl);
    RequerimientoService rsDao = getService(RequerimientoService.class, ApplicationConfig.fakelogicUrl);
    @Resource(name = "jdbc/bladmin")
    private DataSource dataSource;

    @Override
    public List<SimpleDto> all() {
        List<SimpleDto> fakes = rsDao.all();
        String query = "select * from (select ID_MA_REQUERIMIENTO as id, titulo from AD_MA_REQUERIMIENTO order by ID_MA_REQUERIMIENTO desc ) where rownum < 50";
        DbHelper db = new DbHelper(dataSource);
        List<SimpleDto> resultList = db.select(query);
        fakes.addAll(resultList);
        return fakes;
    }

    @Override
    public SimpleDto findById(String id) {
        Long idLong = Long.valueOf(id);
        String query = "select ID_MA_REQUERIMIENTO as id,titulo, descripcion from AD_MA_REQUERIMIENTO where ID_MA_REQUERIMIENTO = ?";
        DbHelper db = new DbHelper(dataSource);
        SimpleDto simpleDto = db.selectById(query, idLong);
        simpleDto.add("casosDeUso", getCasoList(id));
        return simpleDto;
    }

    @Override
    public SimpleDto create(SimpleDto simpleDto) {
        return rsDao.create(simpleDto);
    }

    @Override
    public SimpleDto update(String id, SimpleDto simpleDto) {
        try {
            SimpleDto fakeDao = rsDao.findById(id);
            return rsDao.update(id, simpleDto);
        } catch (Exception e) {
            return rsDao.create(simpleDto);
        }
    }

    @Override
    public SimpleDto delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List getCasoList(Object idRequerimiento) {
        SimpleDto filtro = new SimpleDto().add("id", idRequerimiento);
        List<SimpleDto> casos = cduDao.searchBy(filtro);
        List casoList = new ArrayList();
        for (SimpleDto unCaso : casos) {
            casoList.add(unCaso.get("id"));

        }
        return casoList;
    }
}
