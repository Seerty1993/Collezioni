package org.progetto.dto.blades;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;
import org.progetto.dto.BaseSearchRequest;

@Getter
@Setter
public class SearchTipoBeybladeBladesRequestDTO extends BaseSearchRequest {

    {
        SORT_FIELDS.put("spin", "spin");
        SORT_FIELDS.put("type", "type");
        SORT_FIELDS.put("formato", "formato");
        SORT_FIELDS.put("weight", "weight");
        SORT_FIELDS.put("nameBlades", "nameBlades");
        SORT_FIELDS.put("owned", "owned");
    }

    @QueryParam("spin")
    private String spin;

    @QueryParam("type")
    private String type;

    @QueryParam("formato")
    private String formato;

    @QueryParam("weight")
    private Double weight;

    @QueryParam("nameBlades")
    private String nameBlades;

    @QueryParam("owned")
    private Boolean owned;
}
