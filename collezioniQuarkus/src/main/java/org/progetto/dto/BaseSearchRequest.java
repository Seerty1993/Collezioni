package org.progetto.dto;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BaseSearchRequest {

    @QueryParam("page")
    @DefaultValue("1")
    protected int page;

    @QueryParam("size")
    @DefaultValue("20")
    protected int size;

    @QueryParam("sort")
    protected String sort;

    @QueryParam("descending")
    protected boolean isDescending;

}
