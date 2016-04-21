package ar.gov.santafe.meduc.bladmin.dao.config;

import ar.gov.santafe.meduc.dto.SimpleDto;
import java.util.List;

/**
 *
 * @author enorrmann
 */
public interface GenericDao {
    public int insert(SimpleDto s);
    public int update(SimpleDto s);
    public int delete(SimpleDto s);
    public SimpleDto findById(Long id);
    public List<SimpleDto> all();

}
