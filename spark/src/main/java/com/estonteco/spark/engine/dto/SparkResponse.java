/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.engine.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author APolokh
 */
public class SparkResponse implements Serializable{

    private Collection<IMetric> metrics = new ArrayList<IMetric>();
    
    public SparkResponse() {
    }

    public SparkResponse(Set<IMetric> metrics) {
        this.metrics = metrics;
    }

    public Collection<IMetric> getMetrics() {
        return metrics;
    }

    public void setMetrics(Collection<IMetric> metrics) {
        this.metrics = metrics;
    }
    
    public void addMetric(IMetric metric){
        this.metrics.add(metric);
    }
}
