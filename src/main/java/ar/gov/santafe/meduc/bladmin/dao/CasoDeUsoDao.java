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
public interface CasoDeUsoDao {

    final static String TABLE_NAME = "BL_CASO_DE_USO";

    @SqlQuery("SELECT ID_TEMA FROM BL_TEMA_X_CASO WHERE ID_CASO_DE_USO = :id")
    List<String> getTemas(@Bind("id") Long id);

    @SqlBatch("insert into BL_TEMA_X_CASO (ID_CASO_DE_USO,ID_TEMA) values (:id, :idTemas)")
    void insertTemas(@Bind("id") Long id, @Bind("idTemas") List<Long> idTemas);

    @SqlUpdate("DELETE FROM BL_TEMA_X_CASO WHERE ID_CASO_DE_USO = :id")
    void deleteTemas(@Bind("id") Long id);

    @SqlQuery("SELECT ID_CONDICION FROM BL_PRECONDICION_X_CASO WHERE ID_CASO_DE_USO = :id")
    List<String> getPrecondiciones(@Bind("id") Long id);

    @SqlBatch("insert into BL_PRECONDICION_X_CASO (ID_CASO_DE_USO,ID_CONDICION) values (:id, :idPrecondiciones)")
    void insertPrecondiciones(@Bind("id") Long id, @Bind("idPrecondiciones") List<Long> idPrecondiciones);

    @SqlUpdate("DELETE FROM BL_PRECONDICION_X_CASO WHERE ID_CASO_DE_USO = :id")
    void deletePrecondiciones(@Bind("id") Long id);

    @SqlQuery("SELECT ID_CONDICION FROM BL_POSTCONDICION_X_CASO WHERE ID_CASO_DE_USO = :id")
    List<String> getPostcondiciones(@Bind("id") Long id);

    @SqlBatch("insert into BL_POSTCONDICION_X_CASO (ID_CASO_DE_USO,ID_CONDICION) values (:id, :idPostcondiciones)")
    void insertPostcondiciones(@Bind("id") Long id, @Bind("idPostcondiciones") List<Long> idPostcondiciones);

    @SqlUpdate("DELETE FROM BL_POSTCONDICION_X_CASO WHERE ID_CASO_DE_USO = :id")
    void deletePostcondiciones(@Bind("id") Long id);

    @SqlQuery("SELECT ID_REQUERIMIENTO FROM BL_REQUERIMIENTO_X_CASO WHERE ID_CASO_DE_USO = :id")
    List<String> getRequerimientos(@Bind("id") Long id);

    @SqlBatch("insert into BL_REQUERIMIENTO_X_CASO (ID_CASO_DE_USO,ID_REQUERIMIENTO) values (:id, :idRequerimientos)")
    void insertRequerimientos(@Bind("id") Long id, @Bind("idRequerimientos") List<Long> idRequerimientos);

    @SqlUpdate("DELETE FROM BL_REQUERIMIENTO_X_CASO WHERE ID_CASO_DE_USO = :id")
    void deleteRequerimientos(@Bind("id") Long id);

    @SqlQuery("SELECT SEQ_" + TABLE_NAME + ".nextval FROM DUAL")
    Long getNewId();

    @SqlUpdate("insert into " + TABLE_NAME + " (ID_CASO_DE_USO, NOMBRE, DETALLE ) values (:id, :nombre, :detalle)")
    public void insert(@BindSimpleDto SimpleDto s);

    @SqlUpdate("update " + TABLE_NAME + " set NOMBRE = :nombre , DETALLE =:detalle where ID_CASO_DE_USO = :id")
    public void update(@BindSimpleDto SimpleDto s);

    @SqlQuery("SELECT ID_CASO_DE_USO as id, NOMBRE FROM " + TABLE_NAME )
    public List<SimpleDto> all();

    @SqlQuery("SELECT ID_CASO_DE_USO as id, NOMBRE, DETALLE FROM  " + TABLE_NAME + " where ID_CASO_DE_USO = :id")
    public SimpleDto findById(@Bind("id") Long id);
}
