package ar.gov.santafe.meduc.bladmin.dao;

import ar.gov.santafe.meduc.dto.SimpleDto;
import ar.gov.santafe.simpledb.SimpleDtoMapper;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author enorrmann
 */
@RegisterMapper(SimpleDtoMapper.class)
public interface TicketDao {

    final String TABLE_NAME = "AD_MA_TICKET";

    @SqlQuery("select * from ( select ID_TICKET as id, titulo from " + TABLE_NAME + "  order by ID_TICKET desc ) where rownum < 50")
    public List<SimpleDto> all();

    @SqlQuery("select ID_TICKET, CONSULTA  from " + TABLE_NAME + " where ID_TICKET = :id ")
    public SimpleDto findById(@Bind("id") Long id);

}
