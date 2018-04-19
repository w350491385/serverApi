package com.xw.solr;

import com.xw.base.TestBase;
import com.xw.solr.bean.SolrTestData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Iterator;

/**
 * Created by huangdongbin on 2018/4/13.
 */
public class SolrTest extends TestBase {

    @Autowired
    private SolrTestDataRepository simpleSolrRepository;

    @Test
    public void testFindAll(){
        Iterable<SolrTestData> it = simpleSolrRepository.findAll();
        Iterator<SolrTestData> solrIt =  it.iterator();
        while (solrIt.hasNext()){
            SolrTestData data = solrIt.next();
            System.out.println(data);
        }
    }
    @Test
    public void testFindById(){
       SolrTestData solrTestData = simpleSolrRepository.findOne(4L);
       System.out.println(solrTestData);
    }

    @Test
    public void testSave(){
        SolrTestData solrTestData = new SolrTestData();
        solrTestData.setId(118l);
        solrTestData.setCreateTime(new Timestamp(System.currentTimeMillis()).getTime());
        solrTestData.setPhotoId(20);
        solrTestData.setPluginId("xw:siting");
        simpleSolrRepository.save(solrTestData);
    }

    @Test
    public void testDel(){
        simpleSolrRepository.delete(118l);
    }
}
