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
public interface TipoDocumentoDao {

    final String TABLE_NAME = "BL_TIPO_DOCUMENTO";

    @SqlQuery("SELECT SEQ_" + TABLE_NAME + ".nextval FROM DUAL")
    Long getNewId();

    @SqlUpdate("insert into " + TABLE_NAME + " (ID_TIPO_DOCUMENTO, NOMBRE, RELACION) values (:id_tipo_documento, :nombre, :relacion)")
    public void insert(@BindSimpleDto SimpleDto s);

    @SqlUpdate("delete from " + TABLE_NAME + " where ID_TIPO_DOCUMENTO = :id")
    public void delete(@Bind("id") Long id);

    @SqlQuery("SELECT ID_TIPO_DOCUMENTO, NOMBRE, RELACION FROM " + TABLE_NAME)
    public List<SimpleDto> all();

    @SqlQuery("SELECT ID_TIPO_DOCUMENTO, NOMBRE, RELACION FROM  " + TABLE_NAME + " where ID_TIPO_DOCUMENTO = :id")
    public SimpleDto findById(@Bind("id") Long id);

}
