/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.engine.impl;

import com.estonteco.spark.engine.IDataEngine;
import com.estonteco.spark.engine.IDataSourceManager;
import com.estonteco.spark.engine.dto.IMetric;
import com.estonteco.spark.engine.dto.SparkRequest;
import com.estonteco.spark.engine.dto.SparkResponse;
import com.estonteco.spark.frames.IDataFrame;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author APolokh
 */
public class DataEngine implements IDataEngine<SparkRequest, SparkResponse> {

    private static final int NUMBER_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private IDataSourceManager dataSourceManager;

    public DataEngine(IDataSourceManager dataSourceManager) {
        this.dataSourceManager = dataSourceManager;
    }

    public SparkResponse get(SparkRequest request) {
        Set<String> reqMetricSet = request.getReqMetricSet();
        Collection<IDataFrame> findByMetrics = dataSourceManager.findByMetrics(reqMetricSet);
        ForkJoinPool forkJoinPool = new ForkJoinPool(NUMBER_OF_PROCESSORS);
        MetricCalculateTask metricCalculateTask = new MetricCalculateTask(request, findByMetrics);
        forkJoinPool.execute(metricCalculateTask);
        SparkResponse response = new SparkResponse();
        Collection<IMetric> metrics = forkJoinPool.invoke(metricCalculateTask);
        //todo: add to response exceptions
        if(metricCalculateTask.isCompletedNormally()){
            response.setMetrics(metrics);
        }
        return response;
    }

    public void put(SparkRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(SparkRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void remove(SparkRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
