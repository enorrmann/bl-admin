package ar.gov.santafe.meduc.bladmin.dao;

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
public interface RequerimientoDao {

    final static String TABLE_NAME = "AD_MA_REQUERIMIENTO";

    @SqlQuery("select * from (SELECT ID_MA_REQUERIMIENTO , titulo from " + TABLE_NAME +" order by ID_MA_REQUERIMIENTO desc) where rownum < 300")
    public List<SimpleDto> all();

    @SqlQuery("SELECT ID_MA_REQUERIMIENTO,  titulo, descripcion, re.estado FROM ad_ma_requerimiento req, ad_ma_req_estado re WHERE req.ID_MA_REQUERIMIENTO = :id AND req.AD_ID_MA_REQ_ESTADO   = re.ID_MA_REQ_ESTADO")
    public SimpleDto findById(@Bind("id") Long id);

    @SqlQuery("SELECT ID_DOCUMENTO FROM BL_REQUERIMIENTO_X_DOCUMENTO WHERE ID_MA_REQUERIMIENTO = :id")
    List<String> getCasos(@Bind("id") Long id);

    @SqlQuery("SELECT rxc.ID_MA_REQUERIMIENTO, r.titulo FROM BL_REQUERIMIENTO_X_DOCUMENTO rxc, ad_ma_requerimiento r WHERE r.ID_MA_REQUERIMIENTO = rxc.ID_MA_REQUERIMIENTO and rxc.ID_DOCUMENTO = :idCaso")
    public List<SimpleDto> findByIdCaso(@Bind("idCaso") Long idCaso);
 
    @SqlUpdate("DELETE FROM BL_REQUERIMIENTO_X_DOCUMENTO WHERE ID_MA_REQUERIMIENTO = :idRequerimiento")
    void deleteRequerimientoXCaso(@Bind("idRequerimiento") Long idRequerimiento);

    @SqlBatch("insert into BL_REQUERIMIENTO_X_DOCUMENTO (ID_DOCUMENTO,ID_MA_REQUERIMIENTO) values (:idCasos, :idRequerimiento)")
    void insertRequerimientoXCaso(@Bind("idRequerimiento") Long id, @Bind("idCasos") List<Long> idCasos);

    @SqlQuery("SELECT ID_MA_REQUERIMIENTO,  titulo FROM ad_ma_requerimiento WHERE titulo like :queryString or DESCRIPCION like :queryString")
    public List<SimpleDto> search(@Bind("queryString")String queryString);
}
