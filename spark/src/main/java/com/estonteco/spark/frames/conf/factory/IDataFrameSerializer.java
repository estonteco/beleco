/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory;

import com.estonteco.spark.frames.FrameType;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.conf.IFrameConf;

/**
 *
 * @author mauna
 */
public interface IDataFrameSerializer {

    IFrameConf serialize(IDataFrame dataFrame);

    IDataFrame deserialize(IFrameConf configuration);
    
    FrameType[] support();

}
