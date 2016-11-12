package com.wf.dcs.app.dto;

import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

/**
 * @author ddevera
 */
public class PageDto<T> {

    private boolean hasNextPage;

    private long totalElements;

    private List<T> results;

    private int currentPage;

    private int pageSize;

    public long getCurrentPage() {
        return currentPage;
    }

    /**
     * ?
     *
     * @param currentPage ?
     * @return ?
     */
    public PageDto<T> setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * ?
     *
     * @param pageSize ?
     * @return ?
     */
    public PageDto<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }


    public boolean isHasNextPage() {
        return hasNextPage;
    }

    /**
     * ?
     *
     * @param hasNextPage ?
     * @return ?
     */
    public PageDto<T> setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
        return this;
    }

    public long getTotalElements() {
        return totalElements;
    }

    /**
     * ?
     *
     * @param totalElements ?
     * @return ?
     */
    public PageDto<T> setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public List<T> getResults() {
        return results;
    }

    /**
     * ?
     *
     * @param results ?
     * @return ?
     */
    public PageDto<T> setResults(List<T> results) {
        this.results = results;
        return this;
    }

    /**
     * Creates a PageInfo object.
     *
     * @param paged The Page source
     * @param list  The result list
     * @param <Y>   ?
     * @param <Z>   ?
     * @return ?
     */
    public static <Y, Z> PageDto<Z> newPageInfo(Page<Y> paged, List<Z> list) {
        return new PageDto<Z>().setResults(list)
                .setCurrentPage(paged.getNumber())
                .setTotalElements(paged.getTotalElements())
                .setHasNextPage(paged.hasNext())
                .setPageSize(paged.getSize());

    }

    public static <Y, Z> PageDto<Z> emptyPageInfo() {
        return new PageDto<Z>().setResults(Collections.emptyList());
    }
}
