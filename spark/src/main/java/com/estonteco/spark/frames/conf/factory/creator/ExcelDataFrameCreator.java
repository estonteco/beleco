/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.frames.conf.factory.creator;

import com.estonteco.spark.frames.FrameType;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.excel.ExcelFrameConf;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 *
 * @author APolokh
 */
public class ExcelDataFrameCreator implements IDataFrameCreator<ExcelFrameConf>{

    public IDataFrame create(SQLContext context, ExcelFrameConf configuration) {
        DataFrame table = context.load(null, configuration.getSchema(), configuration.getProperties());
        table.registerTempTable(configuration.getName());
        return null;
    }

    public FrameType support() {
        return FrameType.EXCEL;
    }
    
}
