package org.progetto.dto;

import io.ebean.ExpressionList;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class BaseSearchRequest {

    public Map<String, String> SORT_FIELDS = new HashMap<>();

    @QueryParam("page")
    @DefaultValue("1")
    protected int page;

    @QueryParam("size")
    @DefaultValue("20")
    protected int pageSize;

    @QueryParam("sort")
    protected String sort;

    @QueryParam("descending")
    protected boolean descending;

    public void applySimpleSort(ExpressionList<?> exl) {
        String orderBy = this.sort;
        if (StringUtils.isNotBlank(orderBy)) {
            orderBy = orderBy.trim();
            if (this.descending) {
                orderBy += " desc";
            }
            exl.orderBy(orderBy);
        }
    }

    public void applySimplePagination(ExpressionList<?> exl) {
        exl.setMaxRows(this.pageSize);
        int firstRow = (this.page - 1) * this.pageSize;
        exl.setFirstRow(firstRow);
    }

    public void applySimpleSortAndPagination(ExpressionList<?> exl) {
        applySimpleSort(exl);
        applySimplePagination(exl);
    }

    public void applySortAndPagination(ExpressionList<?> exl) {
        String orderBy = this.sort;
        if (StringUtils.isNotBlank(orderBy)) {
            orderBy = SORT_FIELDS.get(orderBy.trim());
            if (this.descending) {
                orderBy += " desc";
            }
            exl.orderBy(orderBy);
        }
        applySimplePagination(exl);
    }
}
