/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.frames.conf.factory.creator.impl;

import com.estonteco.spark.frames.FrameType;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.IMetaInfo;
import com.estonteco.spark.frames.State;
import com.estonteco.spark.frames.conf.factory.creator.impl.AbstractDataFrameCreator;
import com.estonteco.spark.frames.conf.impl.ConfProperties;
import com.estonteco.spark.frames.conf.impl.DefaultFrameConf;
import com.estonteco.spark.frames.impl.DefaultDataFrame;
import com.estonteco.spark.frames.impl.MetaInfo;
import java.util.Map;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 *
 * @author APolokh
 */
public class CustomDataFrameCreator extends AbstractDataFrameCreator{

    public IDataFrame create(JavaSparkContext sparkContext, SQLContext context, DefaultFrameConf configuration) {
        Map<String, String> properties = configuration.getProperties();
        String query =getValue(properties, ConfProperties.QUERY, "");
        DataFrame table = context.sql(query);
        return new DefaultDataFrame(table, configuration, State.INIT);
    }

    public FrameType support() {
        return FrameType.CUSTOM;
    }
    
}
