package ar.gov.santafe.meduc.bladmin.configuration;

import ar.gov.santafe.meduc.dto.SimpleDto;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author enorrmann
 */
public class WebLayer {

    @Context
    private UriInfo uriInfo;
    
    @Context
    private HttpServletResponse response;

    public List<SimpleDto> paginate(List<SimpleDto> toPaginate) {
        int totalRows = toPaginate.size();
        response.addHeader("X-Total-Count", totalRows + "");
        SimpleDto filterDto = getQueryParams();
        int page = filterDto.getLong("page").intValue();
        int perPage = filterDto.getLong("perPage").intValue();
        int desde = perPage * (page - 1);
        int hasta = desde + perPage - 1;
        if (hasta > totalRows) {
            hasta = totalRows;
        }
        return toPaginate.subList(desde, hasta);

    }
    public SimpleDto getQueryParams() {
        String filters = getFirstParameter("_filters");
        String page = getFirstParameter("_page");
        String perPage = getFirstParameter("_perPage");
        String sortDir = getFirstParameter("_sortDir");
        String sortField = getFirstParameter("_sortField");
        SimpleDto filterDto = filters != null ? new SimpleDto(filters) : new SimpleDto();
        filterDto.add("page", page);
        filterDto.add("perPage", perPage);
        filterDto.add("sortDir", sortDir);
        filterDto.add("sortField", sortField);
        return filterDto;
    }

    private String getFirstParameter(String paramName) {
        return uriInfo.getQueryParameters().getFirst(paramName);
    }

}
