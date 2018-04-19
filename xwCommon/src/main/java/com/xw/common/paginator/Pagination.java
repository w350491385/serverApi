package com.xw.common.paginator;

import java.util.Collection;
import java.util.List;

/**
 * 分页器
 *
 * @author $id$
 * @param <T> 对象
 */
public interface Pagination<T> {

    /**
     * 获取对象总数
     *
     * @return 对象总数
     */
    int getTotalCount();

    /**
     * 获取页面序号
     *
     * @return 页面序号
     */
    int getPageNo();

    /**
     * 获取页面大小, 页面大小>= 实际对象数量
     *
     * @return 页面大小
     */
    int getPageSize();

    /**
     * 获取页面实际对象数量
     *
     * @return 页面内实际对象数量
     */
    int getNumberOfObjects();

    /**
     * 获取分页器内部的对象列表
     *
     * @return 获取分页内的对象列表
     */
    List<T> getObjects();

    /**
     * 添加对象
     *
     * @param obj 分页器内的对象
     */
    void addObject(T obj);

    /**
     * 添加对象集合
     *
     * @param c 对象集合
     * @return 如果列表有改变
     */
    void addAll(Collection<? extends T> c);

    /**
     * 清除分页器内的对象
     */
    void clear();
}
