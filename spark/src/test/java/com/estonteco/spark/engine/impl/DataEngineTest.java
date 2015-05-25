/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.engine.impl;

import com.estonteco.spark.engine.IDataEngine;
import com.estonteco.spark.engine.IDataSourceManager;
import com.estonteco.spark.engine.dto.IMetric;
import com.estonteco.spark.engine.dto.MetricRows;
import com.estonteco.spark.engine.dto.SparkRequest;
import com.estonteco.spark.engine.dto.SparkResponse;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.impl.DefaultDataFrame;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author APolokh
 */
public class DataEngineTest {

    IDataSourceManager dataSourceManager;
    IDataEngine<SparkRequest, SparkResponse> instance;
    Set<String> metricNames = new HashSet<String>() {
        {
            for (int i = 0; i < 30; i++) {
                add("M" + i);
            }
        }
    };

    Collection<IDataFrame> dataFrames = new ArrayList<IDataFrame>() {
        {
            for (int i = 0; i < 10; i++) {
                add(new DummyFrame(i+""));
            }
        }
    };

    public DataEngineTest() {
        dataSourceManager = mock(IDataSourceManager.class);
        when(dataSourceManager.findByMetrics(metricNames)).thenReturn(dataFrames);
        instance = spy(new DataEngine(dataSourceManager));
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of get method, of class DataEngine.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        SparkRequest request = new SparkRequest();
        request.setReqMetricSet(metricNames);
        SparkResponse response = instance.get(request);
        for(IMetric metric:response.getMetrics()){
            System.out.println(metric);
        }
    }

    /**
     * Test of put method, of class DataEngine.
     */
   // @Test
    public void testPut() {
        System.out.println("put");
        SparkRequest request = null;
        DataEngine instance = null;
        instance.put(request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class DataEngine.
     */
   // @Test
    public void testUpdate() {
        System.out.println("update");
        SparkRequest request = null;
        DataEngine instance = null;
        instance.update(request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class DataEngine.
     */
   // @Test
    public void testRemove() {
        System.out.println("remove");
        SparkRequest request = null;
        DataEngine instance = null;
        instance.remove(request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    final class DummyFrame extends DefaultDataFrame {

        private String name;
        public DummyFrame(String name) {
            this.name = name;
        }

        @Override
        public IMetric execute(Object context) {
            MetricRows rows = new MetricRows();
            for (int i = 0; i < 5; i++) {
                Row row = RowFactory.create(name, name+" "+12, name+" "+new Date());
                rows.addRow(row);
            }
            return rows;
        }

    }

}
