/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.engine;

import com.estonteco.spark.frames.IDataFrame;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author mauna
 */
public interface IDataSourceManager {
    
    void register(IDataFrame ds);
    
    void unregister(IDataFrame ds);
    
    Collection<IDataFrame> browseAll();
    
    IDataFrame get(String name);
    
    void handleRegisterException(Exception ex);
    
    Collection<IDataFrame> findByMetrics(Set<String> metricNames);
    
    Object ping(String name);
}
