/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory;

import com.estonteco.spark.engine.IDataSourceManager;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.conf.IDataFrameConf;
import com.estonteco.spark.frames.conf.factory.creator.ExcelDataFrameCreator;
import com.estonteco.spark.frames.conf.factory.serializers.XMLConfSerializer;
import com.estonteco.spark.frames.excel.ExcelFrameConf;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        instance.registerDataFrameCreator(new ExcelDataFrameCreator());
        when(instance.load()).thenReturn(loadByPath(System.getProperty("user.dir")+"/src/main/resources/conf"));
        dataSourceManager = mock(IDataSourceManager.class);
        dataSourceManager.register(any(IDataFrame.class));
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
                IDataFrameConf deserializedConf = XMLConfSerializer.deserialize(fileInputStream,ExcelFrameConf.class);
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
    

//    /**
//     * Test of load method, of class DataFrameSessionContext.
//     */
//    @Test
//    public void testLoad() {
//        System.out.println("load");
//        DataFrameSessionContext instance = new DataFrameSessionContext();
//        Collection<IDataFrameConf> expResult = null;
//        Collection<IDataFrameConf> result = instance.load();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }


    /**
     * Test of getDataFrameCreators method, of class DataFrameSessionContext.
     */
    @Test
    public void testGetDataFrameCreators() {
        System.out.println("getDataFrameCreators");
        Assert.assertTrue(!instance.getDataFrameCreators().isEmpty());
    }
}
