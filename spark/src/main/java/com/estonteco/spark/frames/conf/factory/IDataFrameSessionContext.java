/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory;

import com.estonteco.spark.frames.conf.IDataFrameConf;
import com.estonteco.spark.frames.conf.factory.creator.IDataFrameCreator;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Type of stored session for dataframes processing (has unique identifier).
 * @author mauna
 */
public interface IDataFrameSessionContext {
    
    /**
     * factory initialization (JDBC connect setting, file openning, service call)
     * @param properties 
     */
    void init(Map properties);
    
    Collection<IDataFrameConf> load();
    
    void registerDataFrameCreator(IDataFrameCreator dataFrameCreator);
    
    Collection<IDataFrameCreator> getDataFrameCreators();
    
    String getSessionContextId();
}
