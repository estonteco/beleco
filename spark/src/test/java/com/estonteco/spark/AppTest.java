package com.estonteco.spark;

import java.util.Arrays;
import java.util.List;
import org.apache.spark.api.java.*;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.MetadataBuilder;
import org.apache.spark.sql.types.StructField;

public class AppTest {

    public static void main(String[] args) {
        JavaSparkContext sparkContext = new JavaSparkContext("local", "Test");
        SQLContext sqlContext = new SQLContext(sparkContext);

        List<String> list = Arrays.asList("sdfsdf", "13412", "123123","TTTTT","OOOOO");
        JavaRDD<String> parallelize = sparkContext.parallelize(list);

        JavaRDD<Row> rowRDD = parallelize.map(new org.apache.spark.api.java.function.Function<String, Row>() {

            public Row call(String record) throws Exception {
                return (Row) RowFactory.create(record);
            }
        });

        Metadata metaData = new MetadataBuilder().build();
        StructField columnName = DataTypes.createStructField("NAME", DataTypes.StringType, false, metaData);

        DataFrame name = sqlContext.createDataFrame(rowRDD, DataTypes.createStructType(Arrays.asList(columnName)));
        name.registerTempTable("test");
        DataFrame sql = sqlContext.sql("select * from test where NAME='13412'");
        sql.show();
    }
}
