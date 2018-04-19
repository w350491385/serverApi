package com.xw.solr;


import com.xw.solr.bean.SolrTestData;
import com.xw.solr.repository.BaseSolrRepository;
import org.springframework.data.solr.core.SolrOperations;

/**
 * Created by huangdongbin on 2018/4/13.
 */
public class SolrTestDataRepository extends BaseSolrRepository<SolrTestData,Long> {
    public SolrTestDataRepository(SolrOperations solrOperations, Class<SolrTestData> entityClass) {
        super(solrOperations, entityClass);
    }
}
