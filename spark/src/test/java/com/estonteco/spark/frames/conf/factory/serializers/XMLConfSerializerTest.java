/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.frames.conf.factory.serializers;

import com.estonteco.spark.frames.FrameType;
import com.estonteco.spark.frames.conf.IDataFrameConf;
import com.estonteco.spark.frames.conf.impl.DefaultFrameConf;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author APolokh
 */
public class XMLConfSerializerTest {
    
    public XMLConfSerializerTest() {
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
     * Test of serialize method, of class XMLConfSerializer.
     */
    @Test
    public void testSerialize() {
        System.out.println("serialize");
        DefaultFrameConf conf = new DefaultFrameConf();
        conf.setName("UserConf");
        conf.setType(FrameType.FILE.name());
        Map<String,String> properties = new HashMap<String, String>();
        properties.put("user", "asdasda");
        properties.put("URL", "D:\\test.xsl");
        conf.setProperties(properties);
        
        StructField columnName0 = DataTypes.createStructField("COUNTRY_ID", DataTypes.StringType, false);
        StructField columnName1 = DataTypes.createStructField("NAME", DataTypes.StringType, false);
        StructField columnName2 = DataTypes.createStructField("AGE", DataTypes.IntegerType, false);
        StructType schema = DataTypes.createStructType(Arrays.asList(columnName0,columnName1,columnName2));
        conf.setSchema(schema);
        
        System.out.println(new String(XMLConfSerializer.serialize(conf)));
        InputStream resourceAsStream = XMLConfSerializerTest.class.getResourceAsStream("/conf/userConf.xml");
        IDataFrameConf deserializedConf = XMLConfSerializer.deserialize(resourceAsStream);
        Assert.assertEquals(conf,deserializedConf);
        
        System.out.println(deserializedConf.getSchema().toString());
    }

    
}
