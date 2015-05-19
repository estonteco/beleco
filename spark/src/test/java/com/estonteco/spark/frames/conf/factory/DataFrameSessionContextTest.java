/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory;

import com.estonteco.spark.engine.IDataSourceManager;
import com.estonteco.spark.engine.impl.DataSourceManager;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.State;
import com.estonteco.spark.frames.conf.IDataFrameConf;
import com.estonteco.spark.frames.conf.factory.creator.impl.CustomDataFrameCreator;
import com.estonteco.spark.frames.conf.factory.creator.impl.TextFileFrameCreator;
import com.estonteco.spark.frames.conf.factory.serializers.XMLConfSerializer;
import com.estonteco.spark.frames.conf.impl.DefaultFrameConf;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author APolokh
 */
public class DataFrameSessionContextTest {

    private static DataFrameSessionContext instance;
    private static IDataSourceManager dataSourceManager;

    public DataFrameSessionContextTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        instance = spy(new DataFrameSessionContext());
        instance.registerDataFrameCreator(new TextFileFrameCreator());
        instance.registerDataFrameCreator(new CustomDataFrameCreator());
        when(instance.load()).thenReturn(loadByPath(System.getProperty("user.dir") + "/src/main/resources/conf"));
        dataSourceManager = new DataSourceManager();
        instance.setDataSourceManager(dataSourceManager);
    }

    private static List<IDataFrameConf> loadByPath(String pathName) {
        List<IDataFrameConf> dataFrameConfs = new ArrayList<IDataFrameConf>();
        File path = new File(pathName);

        File[] files = path.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                IDataFrameConf deserializedConf = XMLConfSerializer.deserialize(fileInputStream);
                dataFrameConfs.add(deserializedConf);
            }
        }
        return dataFrameConfs;
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
     * Test of init method, of class DataFrameSessionContext.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        Map properties = new HashMap();
        instance.init(properties);
        Assert.assertNotNull(instance);
    }

    @Test
    public void testBrowseAll() {
        Map properties = new HashMap();
        instance.init(properties);
        System.out.println("browseAll");
        Collection<IDataFrame> browseAll = dataSourceManager.browseAll();
        System.out.println(browseAll);
        Assert.assertNotNull(browseAll);
        for (IDataFrame df : browseAll) {
            Assert.assertTrue(df.getState() == State.REGISTERED);
        }
    }

    @Test
    public void testGetCountry() {
        Map properties = new HashMap();
        instance.init(properties);
        System.out.println("getCountry");
        IDataFrame df = dataSourceManager.get("Countries");
        Assert.assertNotNull(df);
        Row ping = (Row) df.ping();
        System.out.println(ping);
        Collection<Row> execute = df.execute(null);
        for (Row row : execute) {
            System.out.println(row);
        }
    }

    @Test
    public void testGetUser() {
        Map properties = new HashMap();
        instance.init(properties);
        System.out.println("getUser");
        IDataFrame df = dataSourceManager.get("Users");
        Assert.assertNotNull(df);
        Row ping = (Row) df.ping();
        System.out.println(ping);
        Collection<Row> execute = df.execute(null);
        for (Row row : execute) {
            System.out.println(row);
        }
    }

    @Test
    public void testGetView() {
        Map properties = new HashMap();
        instance.init(properties);
        System.out.println("getView");
        IDataFrame df = dataSourceManager.get("View");
        Assert.assertNotNull(df);
        Collection<Row> execute = df.execute(null);
        for (Row row : execute) {
            System.out.println(row);
        }
    
    }

    /**
     * Test of getDataFrameCreators method, of class DataFrameSessionContext.
     */
    @Test
    public void testGetDataFrameCreators() {
        System.out.println("getDataFrameCreators");
        Assert.assertTrue(!instance.getDataFrameCreators().isEmpty());
    }
}
