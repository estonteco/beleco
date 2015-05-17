/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf;

import java.util.Map;

/**
 *
 * @author mauna
 * @param <T> - type of frame metainfo
 */
public interface IFrameConf<T> {
    
   String getName();
   
   String getURL();
   
   Map getProperties();
   
   String getType();
   
   T getSchema();
}
