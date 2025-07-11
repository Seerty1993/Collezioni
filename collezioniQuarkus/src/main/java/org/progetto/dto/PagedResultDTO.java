package org.progetto.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import io.ebean.PagedList;
import io.ebean.typequery.QueryBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagedResultDTO<T> {

    private List<T> list;
    private int totalRows;
    private int totalPages;
    private int pageSize = 20;
    private int page = 1;

    public static <T, R> PagedResultDTO <R> of (PagedList<T> list, Function<? super T,? extends R> mapper) {
        PagedResultDTO<R> pr = new PagedResultDTO<>();
        pr.setList(list.getList().stream()
                .map(mapper)
                .collect(Collectors.toList()));
        pr.setPage(list.getPageIndex() + 1);
        pr.setTotalPages(list.getTotalPageCount());
        pr.setTotalRows(list.getTotalCount());
        return pr;
    }

    public static QueryBean<?,?> setSort(QueryBean<?,?> query, BaseSearchRequest req) {
        if(StringUtils.isNotBlank(req.getSort())) {
            String field = req.getSort().trim();
            if(req.isDescending())
                field += " desc";
            query.orderBy(field);
        }
        return query;
    }

}
