package org.progetto.dto;

import io.ebean.ExpressionList;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Getter
@Setter
public class BeybladeSearchRequest extends SimpleSearchRequest {

    @QueryParam("Id")
    private Long Id;
    @QueryParam("nameitem")
    private String nameitem;
    @QueryParam("description")
    private String description;
    @QueryParam("quantityitem")
    private Integer quantityitem;
    @QueryParam("formato")
    private List<String> formato;
    @QueryParam("ratchet_id")
    private Integer ratchet_id;
    @QueryParam("punta_id")
    private Integer punta_id;
    @QueryParam("blades_id")
    private Integer blades_id;
    @QueryParam("owned")
    private Boolean owned;
    @QueryParam("wish")
    private Boolean wish;
    @QueryParam("category_id")
    private Integer category_id;
    @QueryParam("note")
    private String note;

    public <T> ExpressionList<T> filterBuilder(ExpressionList<T> query) {
        if (Id != null)
            query.eq("Id", Id);
        if (nameitem != null)
            query.eq("nameitem", nameitem);
        if (description != null)
            query.contains("description", description + "%");
        if (quantityitem != null)
            query.eq("quantity", quantityitem);
        if (CollectionUtils.isNotEmpty(formato))
            query.in("formato", formato);
        if (ratchet_id != null)
            query.eq("tipo_beyblade_ratchet.id", ratchet_id);
        if (punta_id != null)
            query.eq("tipo_beyblade_punte.id", punta_id);
        if (blades_id != null)
            query.eq("tipo_beyblade_blades.id", blades_id);
        if (owned != null)
            query.eq("owned", owned);
        if (wish != null)
            query.eq("wish", wish);
        if (category_id != null)
            query.eq("category.id", category_id);
        if (note != null)
            query.contains("note", note + "%");

        return super.paginationOrderAndSort(query);
    }
}
