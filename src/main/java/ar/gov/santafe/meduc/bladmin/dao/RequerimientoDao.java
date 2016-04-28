package ar.gov.santafe.meduc.bladmin.dao;

import ar.gov.santafe.meduc.bladmin.dao.config.SimpleDtoMapper;
import ar.gov.santafe.meduc.dto.SimpleDto;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author enorrmann
 */
@RegisterMapper(SimpleDtoMapper.class)
public interface RequerimientoDao {

    final static String TABLE_NAME = "AD_MA_REQUERIMIENTO";

    @SqlQuery("SELECT ID_MA_REQUERIMIENTO as id, titulo from " + TABLE_NAME +" where rownum < 100")
    public List<SimpleDto> all();

    @SqlQuery("SELECT ID_MA_REQUERIMIENTO as id, titulo, descripcion from " + TABLE_NAME + " where ID_MA_REQUERIMIENTO = :id ")
    public SimpleDto findById(@Bind("id") Long id);

    @SqlQuery("SELECT ID_CASO_DE_USO FROM BL_REQUERIMIENTO_X_CASO WHERE ID_REQUERIMIENTO = :id")
    List<String> getCasos(@Bind("id") Long id);

}
