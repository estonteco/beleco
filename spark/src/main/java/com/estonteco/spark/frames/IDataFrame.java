/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames;

import java.util.Collection;

/**
 * Abstraction is based on Spark Data Frame concept.
 * @author mauna
 * @param <T> - type of field
 */
public interface IDataFrame<T> {
    
    State getState();
    
    void setState(State state);
    
    IMetaInfo getMetaInfo();
    
    T ping();
    
    Collection<T> execute(Object context);
    
}
