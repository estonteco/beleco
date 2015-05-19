/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory.creator.impl;

import com.estonteco.spark.frames.conf.factory.creator.IDataFrameCreator;
import com.estonteco.spark.frames.conf.impl.DefaultFrameConf;
import java.sql.Date;
import java.util.Map;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.DateUtils;

/**
 *
 * @author APolokh
 */
public abstract class AbstractDataFrameCreator implements IDataFrameCreator<DefaultFrameConf> {

    protected <T> T getValue(Map properties, String key, T defaultValue) {
        Object value = properties.get(key);
        return value == null ? defaultValue : (T) value;
    }

    protected Object cast(String value, DataType dataType) {
        if (value == null || dataType == null) {
            return null;
        }
        if (dataType == DataTypes.StringType) {
            return value;
        }
        if (dataType == DataTypes.BooleanType) {
            return Boolean.valueOf(value);
        }
        if (dataType == DataTypes.ByteType) {
            return Byte.valueOf(value);
        }
        if (dataType == DataTypes.DateType) {
            try {
                return DateUtils.fromJavaDate(Date.valueOf(value));
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        if (dataType == DataTypes.DoubleType) {
            return Double.valueOf(value);
        }
        if (dataType == DataTypes.FloatType) {
            return Float.valueOf(value);
        }
        if (dataType == DataTypes.LongType) {
            return Long.valueOf(value);
        }
        if (dataType == DataTypes.ShortType) {
            return Short.valueOf(value);
        }
        if (dataType == DataTypes.TimestampType) {
            // Throw away extra if more than 9 decimal places
            int periodIdx = value.indexOf(".");
            String str = value;
            if (periodIdx != -1 && str.length() - periodIdx > 9) {
                str = str.substring(0, periodIdx + 10);
            }
            try {
                return java.sql.Timestamp.valueOf(str);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        if (dataType == DataTypes.BinaryType) {
            return value.getBytes();
        }
        return null;
    }
}
