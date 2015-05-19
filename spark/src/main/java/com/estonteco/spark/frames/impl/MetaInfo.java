/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.frames.impl;

import com.estonteco.spark.frames.FrameType;
import com.estonteco.spark.frames.IMetaInfo;
import com.estonteco.spark.frames.conf.IDataFrameConf;
import java.util.Arrays;
import java.util.Collection;
import org.apache.spark.sql.types.StructField;

/**
 *
 * @author APolokh
 */
public class MetaInfo implements IMetaInfo<StructField>{

    private final IDataFrameConf configuration;
    private final Collection<StructField> availableFields;
    private final Collection<String> availableFieldsNames;

    public MetaInfo(IDataFrameConf configuration) {
        this.configuration = configuration;
        this.availableFields = Arrays.asList(configuration.getSchema().fields());
        this.availableFieldsNames = Arrays.asList(configuration.getSchema().fieldNames());
    }
    
    public String geName() {
        return configuration.getName();
    }

    public FrameType getType() {
        return FrameType.valueOf(configuration.getType());
    }

    public Collection<StructField> getAvailableFields() {
        return availableFields;
    }

    public IDataFrameConf getConfiguration() {
        return configuration;
    }

    public Collection<String> getAvailableFieldsNames() {
        return availableFieldsNames;
    }
    
}
