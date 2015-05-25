/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.engine.impl;

import com.estonteco.spark.engine.IDataSourceManager;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.State;
import com.sun.istack.logging.Logger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author APolokh
 */
public class DataSourceManager implements IDataSourceManager{
    
    private static Map<String,IDataFrame> registeredDataFrames = new ConcurrentHashMap<String, IDataFrame>();
    private static final Logger LOGGER = Logger.getLogger(DataSourceManager.class);
    
    public void register(IDataFrame df) {
        String name = df.getMetaInfo().geName();
        if(!registeredDataFrames.containsKey(name)
                && df.getState()==State.INIT){
            df.setState(State.REGISTERED);
            registeredDataFrames.put(name, df);
        }
    }

    public void unregister(IDataFrame df) {
         String name = df.getMetaInfo().geName();
        if(registeredDataFrames.containsKey(name)
                && df.getState()==State.REGISTERED){
            df.setState(State.UNREGISTERED);
            registeredDataFrames.remove(name);
        }
    }

    public Collection<IDataFrame> browseAll() {
       return registeredDataFrames.values();
    }

    public IDataFrame get(String name) {
        return registeredDataFrames.get(name);
    }

    public Collection<IDataFrame> findByMetrics(Set<String> metricNames) {
        Set<IDataFrame> frames = new HashSet<IDataFrame>();
        //todo: check priority data frame
        for(IDataFrame df: browseAll()){
            if(df.getMetaInfo().getAvailableFieldsNames().contains(metricNames)){
                frames.add(df);
            }
        }
        return frames;
    }

    
    public void handleRegisterException(Exception ex) {
        LOGGER.warning("Some exception", ex);
    }

    public Object ping(String name) {
        IDataFrame df = get(name);
        if(df!=null){
            return df.ping();
        }
        return null;
    }

    
}
