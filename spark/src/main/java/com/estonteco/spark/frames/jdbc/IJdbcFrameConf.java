/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.jdbc;

import com.estonteco.spark.frames.conf.IFrameConf;

/**
 *
 * @author mauna
 */
public interface IJdbcFrameConf extends IFrameConf {
    
    String getJdbcDriverName();
    
    String getSQLSelectScript();
    
}
