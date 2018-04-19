package com.xw.common.paginator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 分页器实现
 *
 * @author $id$
 * @param <T> 分页内部对象
 */
public class PaginationImpl<T> implements Pagination<T> {

    private final int totalCount;
    private final List<T> objects;
    private final int pageNo;
    private final int pageSize;

    public PaginationImpl(int totalCount, int pageNo, int pageSize) {
        this.objects = new ArrayList<>();
        this.totalCount = totalCount;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    @Override
    public int getTotalCount() {
        return this.totalCount;
    }

    @Override
    public List<T> getObjects() {
        return this.objects;
    }

    @Override
    public void addObject(T obj) {
        this.objects.add(obj);
    }

    @Override
    public void addAll(Collection<? extends T> c) {
        this.objects.addAll(c);
    }

    @Override
    public void clear() {
        this.objects.clear();
    }

    @Override
    public int getPageNo() {
        return this.pageNo;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public int getNumberOfObjects() {
        return this.objects.size();
    }

}
