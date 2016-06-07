package ar.gov.santafe.meduc.bladmin.dao;

import ar.gov.santafe.simpledb.BindSimpleDto;
import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.simpledb.SimpleDtoMapper;
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

    final String TABLE_NAME = "BL_CONDICION";

    @SqlQuery("SELECT ID_CONDICION_DEPENDE FROM BL_CONDICION_DEPENDE WHERE ID_CONDICION =:ID_CONDICION")
    List<String> getDependencias(@Bind("ID_CONDICION") Long idCondicon);

    @SqlQuery("SELECT SEQ_BL_CONDICION.nextval FROM DUAL")
    Long getNewId();

    @SqlBatch("insert into BL_CONDICION_DEPENDE (ID_CONDICION, ID_CONDICION_DEPENDE) values (:idCondicion, :idCondicionDepende)")
    void insertDependencias(@Bind("idCondicion") Long idCondicion, @Bind("idCondicionDepende") List<Long> idCondicionDepende);

    @SqlUpdate("DELETE FROM BL_CONDICION_DEPENDE WHERE ID_CONDICION = :idCondicion")
    void deleteDependencias(@Bind("idCondicion") Long idCondicion);

    @SqlUpdate("insert into BL_CONDICION (ID_CONDICION, nombre, descripcion,implementacion) values (:id, :nombre, :descripcion, :implementacion)")
    public void insert(@BindSimpleDto SimpleDto s);

    @SqlUpdate("DELETE FROM BL_CONDICION WHERE ID_CONDICION = :idCondicion")
    void deleteCondicion(@Bind("idCondicion") Long idCondicion);

    @SqlQuery("select ID_CONDICION ,NOMBRE, DESCRIPCION from BL_CONDICION")
    public List<SimpleDto> all();

    @SqlQuery("select ID_CONDICION ,NOMBRE, DESCRIPCION, implementacion from BL_CONDICION where ID_CONDICION = :idCondicion")
    public SimpleDto findById(@Bind("idCondicion")Long idCondicion);

    @SqlQuery("select ID_CONDICION ,NOMBRE, DESCRIPCION from BL_CONDICION where ID_CONDICION in (:idCondicionList)")
    public List<SimpleDto> findByIdIn(@Bind("idCondicionList")List<Long> idCondicionList);
    
    @SqlUpdate("update " + TABLE_NAME + " set NOMBRE = :nombre, DESCRIPCION = :descripcion, implementacion = :implementacion where ID_CONDICION = :id")
    public void update(@BindSimpleDto SimpleDto s);

}
