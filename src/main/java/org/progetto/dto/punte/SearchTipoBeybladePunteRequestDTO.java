package org.progetto.dto.punte;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;
import org.progetto.dto.BaseSearchRequest;

@Getter
@Setter
public class SearchTipoBeybladePunteRequestDTO extends BaseSearchRequest {

    {
        SORT_FIELDS.put("namePoint", "namePoint");
        SORT_FIELDS.put("weight", "weight");
        SORT_FIELDS.put("originBeyFrom", "originBeyFrom");
        SORT_FIELDS.put("formato", "formato");
        SORT_FIELDS.put("owned", "owned");
    }

    @QueryParam("namePoint")
    private String namePoint;

    @QueryParam("weight")
    private Double weight;

    @QueryParam("originBeyFrom")
    private String originBeyFrom;

    @QueryParam("formato")
    private String formato;

    @QueryParam("owned")
    private Boolean owned;
}
