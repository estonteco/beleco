/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf;

import java.util.Map;
import org.apache.spark.sql.types.StructType;

/**
 *
 * @author mauna
 */

public interface IDataFrameConf {

    String getName();

    void setName(String name);

    String getURL();

    void setURL(String URL);

    Map<String,String> getProperties();

    void setProperties(Map<String,String> properties);

    String getType();

    void setType(String type);

    StructType getSchema();

    void setSchema(StructType schema);
}
