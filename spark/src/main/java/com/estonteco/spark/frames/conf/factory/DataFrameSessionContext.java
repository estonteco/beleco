/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory;

import com.estonteco.spark.engine.IDataSourceManager;
import com.estonteco.spark.frames.FrameType;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.conf.IDataFrameConf;
import com.estonteco.spark.frames.conf.factory.creator.IDataFrameCreator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author APolokh
 */
public class DataFrameSessionContext implements IDataFrameSessionContext {

    private final Map<FrameType,IDataFrameCreator> dataFrameCreatorsMap
            = new EnumMap<FrameType,IDataFrameCreator>(FrameType.class);
    private String sessionContextId;
    private IDataSourceManager dataSourceManager;
    private Map properties;

    public void init(Map properties) {
        this.properties = properties;
        //load configuration (by credentials)
        Collection<IDataFrameConf> configurations = load();
        for(IDataFrameConf conf: configurations){
            FrameType frameType = FrameType.valueOf(conf.getType());
            IDataFrameCreator dataFrameCreator = dataFrameCreatorsMap.get(frameType);
            IDataFrame dataFrame = dataFrameCreator.create(conf);
            dataSourceManager.register(dataFrame);
        }
    }

    public Collection<IDataFrameConf> load() {
        return new ArrayList<IDataFrameConf>();
    }

    
    public void registerDataFrameCreator(IDataFrameCreator dataFrameCreator) {
        dataFrameCreatorsMap.put(dataFrameCreator.support(),dataFrameCreator);
    }

    public Collection<IDataFrameCreator> getDataFrameCreators() {
        return dataFrameCreatorsMap.values();
    }

    public String getSessionContextId() {
        return sessionContextId;
    }

    public IDataSourceManager getDataSourceManager() {
        return dataSourceManager;
    }

    public void setDataSourceManager(IDataSourceManager dataSourceManager) {
        this.dataSourceManager = dataSourceManager;
    }

    
}
