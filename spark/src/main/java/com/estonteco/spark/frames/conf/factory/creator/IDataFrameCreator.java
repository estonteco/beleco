/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory.creator;

import com.estonteco.spark.frames.FrameType;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.conf.IDataFrameConf;
import java.io.Serializable;
import java.util.Map;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;

/**
 *
 * @author mauna
 * @param <T>
 */
public interface IDataFrameCreator<T extends IDataFrameConf> extends Serializable{

    IDataFrame create(JavaSparkContext sparkContext, SQLContext context, T configuration);
    
    FrameType support();

}
