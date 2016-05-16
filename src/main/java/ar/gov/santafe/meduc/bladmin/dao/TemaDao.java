package ar.gov.santafe.meduc.bladmin.dao;

import ar.gov.santafe.meduc.bladmin.dao.config.BindSimpleDto;
import ar.gov.santafe.meduc.bladmin.dao.config.SimpleDtoMapper;
import ar.gov.santafe.meduc.dto.SimpleDto;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author enorrmann
 */
@RegisterMapper(SimpleDtoMapper.class)
public interface TemaDao {

    final String TABLE_NAME = "BL_TEMA";

    @SqlQuery("select ID_TEMA , NOMBRE, ID_TEMA_PADRE from " + TABLE_NAME + " where rownum < 100")
    public List<SimpleDto> all();

    @SqlQuery("select ID_TEMA , NOMBRE, ID_TEMA_PADRE from " + TABLE_NAME + " where ID_TEMA = :id ")
    public SimpleDto findById(@Bind("id") Long id);

    @SqlQuery("SELECT SEQ_" + TABLE_NAME + ".nextval FROM DUAL")
    Long getNewId();

    @SqlUpdate("insert into " + TABLE_NAME + " (ID_tema, nombre, ID_tema_padre) values (:id_tema, :nombre, :id_tema_padre)")
    public void insert(@BindSimpleDto SimpleDto s);

    @SqlUpdate("DELETE FROM BL_TEMA WHERE ID_TEMA = :id")
    void delete(@Bind("id") Long id);

    @SqlQuery("select ID_TEMA , NOMBRE, ID_TEMA_PADRE from " + TABLE_NAME + " where ID_TEMA in (:id) ")
    public List<SimpleDto> findByIdIn(@Bind("id") String id);

}
