/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames;

import java.util.Set;

/**
 * Abstraction is based on Spark Data Frame concept.
 * @author mauna
 */
public interface IDataFrame {
    
    State getState();
    
    IMetaInfo getMetaInfo();
    
    void ping();
    
    Set execute(Object context);
    
}
