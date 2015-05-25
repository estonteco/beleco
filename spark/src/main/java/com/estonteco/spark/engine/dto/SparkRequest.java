/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.engine.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author APolokh
 */
public class SparkRequest implements Serializable{
    
    private Set<String> reqMetricSet = new HashSet<String>();

    public SparkRequest() {
    }

    public Set<String> getReqMetricSet() {
        return reqMetricSet;
    }

    public void setReqMetricSet(Set<String> reqMetricSet) {
        this.reqMetricSet = reqMetricSet;
    }
    
    public void addReqMetric(String name){
        this.reqMetricSet.add(name);
    }
    
    public boolean isEmpty(){
        return reqMetricSet==null|| reqMetricSet.isEmpty();
    }
}
