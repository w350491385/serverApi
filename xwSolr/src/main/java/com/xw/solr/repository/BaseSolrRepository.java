package com.xw.solr.repository;

import org.apache.solr.common.SolrInputDocument;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.data.solr.repository.query.SolrEntityInformation;
import org.springframework.data.solr.repository.support.SolrEntityInformationCreatorImpl;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * Created by huangdongbin on 2018/4/13.
 */
public class BaseSolrRepository<T, ID extends Serializable> implements SolrCrudRepository<T, ID> {

    private String solrCoreName;
    private static final String DEFAULT_ID_FIELD = "id";
    private SolrOperations solrOperations;
    private String idFieldName = DEFAULT_ID_FIELD;
    private Class<T> entityClass;
    private SolrEntityInformation<T, ?> entityInformation;

    public BaseSolrRepository(SolrOperations solrOperations, Class entityClass) {
        SolrEntityInformation<T, ?> metadata = getEntityInformation(entityClass);
        this.solrOperations = solrOperations;
        this.entityInformation = metadata;
        this.idFieldName = this.entityInformation.getIdAttribute();
        this.entityClass = this.entityInformation.getJavaType();
        this.solrCoreName = metadata.getSolrCoreName();
    }

    @Override
    public long count() {
        return count(new SimpleQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD)));
    }

    protected long count(org.springframework.data.solr.core.query.Query query) {
        org.springframework.data.solr.core.query.Query countQuery = SimpleQuery.fromQuery(query);
        return solrOperations.count(solrCoreName, countQuery);
    }

    private static SolrEntityInformation getEntityInformation(Class type) {
        return new SolrEntityInformationCreatorImpl(new SimpleSolrMappingContext()).getEntityInformation(type);
    }

    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, "Cannot save 'null' entity.");
        this.solrOperations.saveBean(entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        Assert.notNull(entities, "Cannot insert 'null' as a List.");
        if (!(entities instanceof Collection<?>)) {
            throw new InvalidDataAccessApiUsageException("Entities have to be inside a collection");
        }
        this.solrOperations.saveBeans((Collection<? extends T>) entities);
        return entities;
    }

    @Override
    public T findOne(ID id) {
        return solrOperations.queryForObject(solrCoreName,new SimpleQuery(new Criteria(this.idFieldName).is(id)), this.entityClass);
    }

    @Override
    public boolean exists(ID id) {
        return findOne(id) != null;
    }

    @Override
    public Iterable<T> findAll() {
        int itemCount = (int) this.count();
        if (itemCount == 0) {
            return new PageImpl<T>(Collections.<T>emptyList());
        }
        return this.findAll(new SolrPageRequest(0, itemCount));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return solrOperations.queryForPage(solrCoreName,
                new SimpleQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD)).setPageRequest(pageable),
                entityClass);
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        org.springframework.data.solr.core.query.Query query = new SimpleQuery(new Criteria(this.idFieldName).in(ids));
        query.setPageRequest(new SolrPageRequest(0, (int) count(query)));
        return solrOperations.queryForPage(solrCoreName,query, entityClass);
    }

    @Override
    public void delete(ID id) {
        solrOperations.deleteById(solrCoreName,id.toString());
    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "Cannot delete 'null' entity.");
        delete(Arrays.asList(entity));
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        Assert.notNull(entities, "Cannot delete 'null' list.");
        ArrayList<String> idsToDelete = new ArrayList<String>();
        for (T entity : entities) {
            idsToDelete.add(extractIdFromBean(entity).toString());
        }
        this.solrOperations.deleteById(solrCoreName,idsToDelete);
    }

    private Object extractIdFromBean(T entity) {
        if (entityInformation != null) {
            return entityInformation.getId(entity);
        }

        SolrInputDocument solrInputDocument = this.solrOperations.convertBeanToSolrInputDocument(entity);
        return extractIdFromSolrInputDocument(solrInputDocument);
    }

    private String extractIdFromSolrInputDocument(SolrInputDocument solrInputDocument) {
        Assert.notNull(solrInputDocument.getField(idFieldName), "Unable to find field '" + idFieldName
                + "' in SolrDocument.");
        Assert.notNull(solrInputDocument.getField(idFieldName).getValue(), "ID must not be 'null'.");

        return solrInputDocument.getField(idFieldName).getValue().toString();
    }

    @Override
    public void deleteAll() {
        this.solrOperations.delete(solrCoreName,new SimpleFilterQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD)));
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
        int itemCount = (int) this.count();
        if (itemCount == 0) {
            return new PageImpl<T>(Collections.<T>emptyList());
        }
        return solrOperations.queryForPage(solrCoreName,
                new SimpleQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD)).setPageRequest(
                        new SolrPageRequest(0, itemCount)).addSort(sort), entityClass);
    }

}
