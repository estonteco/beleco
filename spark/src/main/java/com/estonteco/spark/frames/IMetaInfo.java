/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames;

import com.estonteco.spark.frames.conf.IDataFrameConf;
import java.util.Collection;

/**
 * This class contains meta information about some dataframe. (set of the
 * abailable fields, name e.t.c)
 *
 * @author mauna
 * @param <T> - type of field's information.
 */
public interface IMetaInfo<T> {

    String geName();
    
    FrameType getType();
     
    Collection<T> getAvailableFields();
    
    Collection<String> getAvailableFieldsNames();

    IDataFrameConf getConfiguration();
}
