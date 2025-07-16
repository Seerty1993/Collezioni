package org.progetto.dto;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTipoBeybladeRatchetRequestDTO extends BaseSearchRequest{

    {
        SORT_FIELDS.put("Bumps", "Bumps");
        SORT_FIELDS.put("Height", "Height");
        SORT_FIELDS.put("originBeyFrom", "originBeyFrom");
    }

    @QueryParam("Bumps")
    private Integer bumps;

    @QueryParam("Height")
    private Integer height;

    @QueryParam("originBeyFrom")
    private String originBeyFrom;
}
