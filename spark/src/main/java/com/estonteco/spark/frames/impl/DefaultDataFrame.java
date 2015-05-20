/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.frames.impl;

import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.IMetaInfo;
import com.estonteco.spark.frames.State;
import com.estonteco.spark.frames.conf.IDataFrameConf;
import java.util.Collection;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;

/**
 *
 * @author APolokh
 */
public class DefaultDataFrame implements IDataFrame<Row>{

    private IMetaInfo metaInfo;
    private DataFrame table;
    private State state = State.UNKNOWN;

    public DefaultDataFrame() {
    }

    public DefaultDataFrame(DataFrame table, IDataFrameConf conf) {
        this.table = table;
        this.metaInfo = new MetaInfo(conf);
    }
    
     public DefaultDataFrame(DataFrame table, IMetaInfo metaInfo,State state) {
        this.table = table;
        this.metaInfo = metaInfo;
        this.state = state;
    }
     
     public DefaultDataFrame(DataFrame table,IDataFrameConf conf,State state) {
        this.table = table;
        this.metaInfo = new MetaInfo(conf);
        this.state = state;
    }
    
    
    public State getState() {
       return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    
    public IMetaInfo getMetaInfo() {
        return metaInfo;
    }

    public Row ping() {
        return table.head();
    }

    public Collection<Row> execute(Object context) {
        return table.collectAsList();
    }

    
}
