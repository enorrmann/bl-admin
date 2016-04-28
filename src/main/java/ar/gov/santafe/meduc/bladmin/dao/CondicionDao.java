package ar.gov.santafe.meduc.bladmin.dao;

import ar.gov.santafe.meduc.bladmin.dao.config.BindSimpleDto;
import ar.gov.santafe.meduc.bladmin.dao.config.SimpleDtoMapper;
import ar.gov.santafe.meduc.dto.SimpleDto;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author enorrmann
 */
@RegisterMapper(SimpleDtoMapper.class)
public interface CondicionDao {

    @SqlQuery("SELECT ID_CONDICION_DEPENDE FROM BL_CONDICION_DEPENDE WHERE ID_CONDICION =:ID_CONDICION")
    List<String> getDependencias(@Bind("ID_CONDICION") Long idCondicon);

    @SqlQuery("SELECT SEQ_BL_CONDICION.nextval FROM DUAL")
    Long getNewId();

    @SqlBatch("insert into BL_CONDICION_DEPENDE (ID_CONDICION, ID_CONDICION_DEPENDE) values (:idCondicion, :idCondicionDepende)")
    void insertDependencias(@Bind("idCondicion") Long idCondicion, @Bind("idCondicionDepende") List<Long> idCondicionDepende);

    @SqlUpdate("DELETE FROM BL_CONDICION_DEPENDE WHERE ID_CONDICION = :idCondicion")
    void deleteDependencias(@Bind("idCondicion") Long idCondicion);

    @SqlUpdate("insert into BL_CONDICION (ID_CONDICION, nombre, descripcion,implementacion) values (:id_condicion, :nombre, :descripcion, :implementacion)")
    public void insert(@BindSimpleDto SimpleDto s);

    @SqlUpdate("DELETE FROM BL_CONDICION WHERE ID_CONDICION = :idCondicion")
    void deleteCondicion(@Bind("idCondicion") Long idCondicion);

    @SqlQuery("select ID_CONDICION as id,NOMBRE, DESCRIPCION from BL_CONDICION")
    public List<SimpleDto> all();

    @SqlQuery("select ID_CONDICION as id,NOMBRE, DESCRIPCION from BL_CONDICION where ID_CONDICION = :idCondicion")
    public SimpleDto findById(@Bind("idCondicion")Long idCondicion);
}
