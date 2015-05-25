/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.engine.impl;

import com.estonteco.spark.engine.dto.IMetric;
import com.estonteco.spark.engine.dto.SparkRequest;
import com.estonteco.spark.frames.IDataFrame;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author APolokh
 */
public class MetricCalculateTask extends RecursiveTask<Collection<IMetric>> {

    private final SparkRequest request;
    private final Collection<IDataFrame> dataFrames;

    public MetricCalculateTask(SparkRequest request, Collection<IDataFrame> dataFrames) {
        this.request = request;
        this.dataFrames =  dataFrames;
    }

    @Override
    protected Collection<IMetric> compute() {
        Collection<IMetric> result = new ArrayList<IMetric>();
        List<RecursiveTask> tasks = new ArrayList<RecursiveTask>();
        if (dataFrames.size()==1) {
            IMetric resultSet = dataFrames.iterator().next().execute(request);
            result.add(resultSet);
            return result;
        } else {
             for(IDataFrame df: dataFrames){
                 MetricCalculateTask task = new MetricCalculateTask(request, Collections.singletonList(df));
                 task.fork();
                 tasks.add(task);
             }
        }
        addResultsFromTasks(result,tasks);
        return result;
    }

    private void addResultsFromTasks(Collection<IMetric> list, List<RecursiveTask> tasks) {
        for (RecursiveTask<Collection<IMetric>> item : tasks) {
            list.addAll(item.join());
        }
    }
}
