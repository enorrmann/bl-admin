package ar.gov.santafe.meduc.bladmin.dao;

import ar.gov.santafe.meduc.bladmin.dao.config.GenericDao;
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
public interface CasoDeUsoDao extends GenericDao {

    final static String TABLA = "BL_CASO_DE_USO";
    final static String SECUENCIA = "SEQ_BL_CASO_DE_USO.nextval";

    @Override
    @SqlUpdate("insert into " + TABLA + " (id,nombre, detalle) values (" + SECUENCIA + ", :nombre, :detalle)")
    public int insert(@BindSimpleDto SimpleDto s);

    @Override
    @SqlUpdate("update " + TABLA + " set nombre=:nombre, detalle=:detalle where id=:id")
    public int update(@BindSimpleDto SimpleDto s);

    @Override
    @SqlQuery("select * from "+TABLA+" where id = :id")
    public SimpleDto findById(@Bind("id")Long id);

    @Override
    @SqlQuery("select * from "+TABLA)
    public List<SimpleDto> all();

}
