/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.frames.conf.factory.creator.impl;

import com.estonteco.spark.frames.FrameType;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.State;
import com.estonteco.spark.frames.conf.factory.creator.impl.AbstractDataFrameCreator;
import com.estonteco.spark.frames.conf.impl.DefaultFrameConf;
import com.estonteco.spark.frames.impl.DefaultDataFrame;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 *
 * @author APolokh
 */
@Experimental
public class ExcelDataFrameCreator extends AbstractDataFrameCreator{

    public IDataFrame create(JavaSparkContext javaSparkContext,SQLContext context, DefaultFrameConf configuration) {
        //TODO: excel loading
        DataFrame table = context.load(null, configuration.getSchema(), configuration.getProperties());
        table.registerTempTable(configuration.getName());
        return new DefaultDataFrame(table,configuration, State.INIT);
    }

    public FrameType support() {
        return FrameType.EXCEL;
    }
   
}
