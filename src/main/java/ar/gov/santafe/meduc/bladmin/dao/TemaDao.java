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
public interface TemaDao {

    final static String TABLE_NAME = "BL_TEMA";

    @SqlQuery("select ID_TEMA as id, NOMBRE, ID_TEMA_PADRE from " + TABLE_NAME +" where rownum < 100")
    public List<SimpleDto> all();

    @SqlQuery("select ID_TEMA as id, NOMBRE, ID_TEMA_PADRE from " + TABLE_NAME + " where ID_TEMA = :id ")
    public SimpleDto findById(@Bind("id") Long id);

}
