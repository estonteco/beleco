/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory.creator.impl;

import com.estonteco.spark.frames.FrameType;
import com.estonteco.spark.frames.IDataFrame;
import com.estonteco.spark.frames.State;
import com.estonteco.spark.frames.conf.impl.DefaultFrameConf;
import com.estonteco.spark.frames.impl.DefaultDataFrame;
import java.util.Map;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

/**
 *
 * @author APolokh
 */
public class TextFileFrameCreator extends AbstractDataFrameCreator {

    public IDataFrame create(JavaSparkContext javaSparkContext, SQLContext context, DefaultFrameConf configuration) {
        Map<String, String> properties = configuration.getProperties();
        String url = getValue(properties, "URL", "");
        JavaRDD<String> textFile = javaSparkContext.textFile(url);
        final StructType schema = configuration.getSchema();
        final String delimiter = getValue(properties, "delimiter", ",");
        final boolean readHeader = Boolean.valueOf(getValue(properties, "header", "false"));
        JavaRDD<Row> rows = textFile.map(new Function<String, Row>() {
            long rowIndex = 0;

            public Row call(String record) throws Exception {
                Object[] cells = new Object[schema.size()];
                if (!readHeader && rowIndex++ == 0) {
                    return RowFactory.create(cells);
                }
                String[] parts = record.split(delimiter);
                int i = 0;
                for (StructField sf : schema.fields()) {
                    String cell = parts[i];
                    if (sf.nullable() && (cell == null || cell.isEmpty())) {
                        cells[i] = null;
                    } else {
                        cells[i] = cast(cell, sf.dataType());
                    }
                    i++;
                }
                return RowFactory.create(cells);
            }
        });
        DataFrame table = context.createDataFrame(rows, schema);
        table.registerTempTable(configuration.getName());
        if (configuration.isCache()) {
            table.cache();
        }
        return new DefaultDataFrame(table, configuration, State.INIT);
    }

    public FrameType support() {
        return FrameType.FILE;
    }

}
