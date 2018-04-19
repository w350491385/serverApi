package com.xw.solr.bean;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Created by huangdongbin on 2018/4/13.
 */
@SolrDocument(solrCoreName = "example")
public class SolrTestData {

    @Field
    private long id;//案例id
    @Field
    private int photoId;//案例图片 转店没有时,找转店第一张照片
    @Field
    private long createTime;//创建时间 (案例主表的创建时间)
    @Field
    private long startTime;//服务开始时间
    @Field
    private long endTime;//服务结束时间
    @Field
    private int cycle;//服务周期
    @Field
    private int category;//案例分类（0 普通案例，1 忠诚案例)
    @Field
    private int hasArticle;//是否有文章 1:有:0:没有
    @Field
    private int star;//案例星级，0-5，默认为0星
    @Field
    private String pluginId;//
    @Field
    private int cityId;
    @Field
    private int hasSuccess;
    @Field
    private int quoteMode;
    @Field
    private int opinion;
    @Field
    private int hasVedio;

    public int getOpinion() {
        return opinion;
    }

    public void setOpinion(int opinion) {
        this.opinion = opinion;
    }

    public int getHasArticle() {
        return hasArticle;
    }

    public void setHasArticle(int hasArticle) {
        this.hasArticle = hasArticle;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getPluginId() {
        return pluginId;
    }

    public void setPluginId(String pluginId) {
        this.pluginId = pluginId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getHasSuccess() {
        return hasSuccess;
    }

    public void setHasSuccess(int hasSuccess) {
        this.hasSuccess = hasSuccess;
    }

    public int getQuoteMode() {
        return quoteMode;
    }

    public void setQuoteMode(int quoteMode) {
        this.quoteMode = quoteMode;
    }

    public int getHasVedio() {
        return hasVedio;
    }

    public void setHasVedio(int hasVedio) {
        this.hasVedio = hasVedio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
