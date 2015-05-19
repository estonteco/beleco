/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.frames.conf.factory.creator.impl;

import com.estonteco.spark.frames.conf.factory.creator.IDataFrameCreator;
import com.estonteco.spark.frames.conf.impl.DefaultFrameConf;
import java.util.Map;

/**
 *
 * @author APolokh
 */
public abstract class AbstractDataFrameCreator implements IDataFrameCreator<DefaultFrameConf>{
    
    protected  <T>T getValue(Map properties,String key, T defaultValue){
        Object value = properties.get(key);
        return value==null? defaultValue:(T)value;
    }
    
}
