/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.engine.dto;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.spark.sql.Row;

/**
 *
 * @author APolokh
 */
public class MetricRows implements IMetric {

    private Collection<Row> rows = new ArrayList<Row>();

    public MetricRows() {
    }

    public MetricRows(Collection<Row> rows) {
        this.rows = rows;
    }

    public Collection<Row> getRows() {
        return rows;
    }

    public void setRows(Collection<Row> rows) {
        this.rows = rows;
    }

    public void addRow(Row row) {
        if (rows != null) {
            this.rows.add(row);
        }
    }

    @Override
    public String toString() {
        return "MetricRows{" + "rows=" + rows + '}';
    }

   

}
