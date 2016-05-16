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
public interface DocumentoDao {

    final String TABLE_NAME = "BL_DOCUMENTO";

    @SqlQuery("SELECT ID_TEMA FROM BL_TEMA_X_DOCUMENTO WHERE ID_DOCUMENTO = :id")
    List<String> getTemas(@Bind("id") Long id);

    @SqlBatch("insert into BL_TEMA_X_DOCUMENTO (ID_DOCUMENTO,ID_TEMA) values (:id, :idTemas)")
    void insertTemas(@Bind("id") Long id, @Bind("idTemas") List<Long> idTemas);

    @SqlUpdate("DELETE FROM BL_TEMA_X_DOCUMENTO WHERE ID_DOCUMENTO = :id")
    void deleteTemas(@Bind("id") Long id);

    /*@SqlQuery("SELECT ID_CONDICION FROM BL_PRECONDICION_X_DOCUMENTO WHERE ID_DOCUMENTO = :id")
    List<String> getPrecondiciones(@Bind("id") Long id);

    @SqlBatch("insert into BL_PRECONDICION_X_DOCUMENTO (ID_DOCUMENTO,ID_CONDICION) values (:id, :idPrecondiciones)")
    void insertPrecondiciones(@Bind("id") Long id, @Bind("idPrecondiciones") List<Long> idPrecondiciones);

    @SqlUpdate("DELETE FROM BL_PRECONDICION_X_DOCUMENTO WHERE ID_DOCUMENTO = :id")
    void deletePrecondiciones(@Bind("id") Long id);

    @SqlQuery("SELECT ID_CONDICION FROM BL_POSTCONDICION_X_DOCUMENTO WHERE ID_DOCUMENTO = :id")
    List<String> getPostcondiciones(@Bind("id") Long id);

    @SqlBatch("insert into BL_POSTCONDICION_X_DOCUMENTO (ID_DOCUMENTO,ID_CONDICION) values (:id, :idPostcondiciones)")
    void insertPostcondiciones(@Bind("id") Long id, @Bind("idPostcondiciones") List<Long> idPostcondiciones);

    @SqlUpdate("DELETE FROM BL_POSTCONDICION_X_DOCUMENTO WHERE ID_DOCUMENTO = :id")
    void deletePostcondiciones(@Bind("id") Long id);*/

    @SqlQuery("SELECT ID_MA_REQUERIMIENTO FROM BL_REQUERIMIENTO_X_DOCUMENTO WHERE ID_DOCUMENTO = :id")
    List<String> getRequerimientos(@Bind("id") Long id);

    @SqlBatch("insert into BL_REQUERIMIENTO_X_DOCUMENTO (ID_DOCUMENTO,ID_MA_REQUERIMIENTO) values (:id, :idRequerimientos)")
    void insertRequerimientos(@Bind("id") Long id, @Bind("idRequerimientos") List<Long> idRequerimientos);

    @SqlUpdate("DELETE FROM BL_REQUERIMIENTO_X_DOCUMENTO WHERE ID_DOCUMENTO = :id")
    void deleteRequerimientos(@Bind("id") Long id);

    @SqlQuery("SELECT SEQ_" + TABLE_NAME + ".nextval FROM DUAL")
    Long getNewId();

    @SqlUpdate("insert into " + TABLE_NAME + " (ID_DOCUMENTO, NOMBRE, DETALLE, TEMAS,ID_TIPO_DOCUMENTO ) values (:id_documento, :nombre, :detalle, :temas, :id_tipo_documento)")
    public void insert(@BindSimpleDto SimpleDto s);

    @SqlUpdate("delete from " + TABLE_NAME + " where ID_DOCUMENTO = :id")
    public void delete(@Bind("id") Long id);

    @SqlQuery("SELECT ID_DOCUMENTO, NOMBRE, temas, id_tipo_documento FROM " + TABLE_NAME)
    public List<SimpleDto> all();

    @SqlQuery("SELECT ID_DOCUMENTO, NOMBRE, DETALLE, id_tipo_documento, TEMAS FROM  " + TABLE_NAME + " where ID_DOCUMENTO = :id")
    public SimpleDto findById(@Bind("id") Long id);

    @SqlQuery("SELECT ID_DOCUMENTO, NOMBRE ,id_tipo_documento, temas FROM  "+TABLE_NAME+" c where temas like '%'||:idTema||'%'")
    public List<SimpleDto> findByIdTema(@Bind("idTema") Long idTema);

    @SqlQuery("SELECT ID_DOCUMENTO, NOMBRE ,id_tipo_documento, temas FROM  "+TABLE_NAME+" c where id_tipo_documento = :idTipoDocumento")
    public List<SimpleDto> findByIdTipoDocumento(@Bind("idTipoDocumento") Long idTipoDocumento);

    @SqlQuery("SELECT ID_DOCUMENTO, NOMBRE ,id_tipo_documento, temas FROM  "+TABLE_NAME+" c where temas like '%'||:idTema||'%' and id_tipo_documento = :idTipoDocumento ")
    public List<SimpleDto> findByIdTemaAndTipoDocumento(@Bind("idTema") Long idTema,@Bind("idTipoDocumento") Long idTipoDocumento);

}
