/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estonteco.spark.frames.conf.factory.serializers;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APolokh
 */
@XmlRootElement(name = "columns")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListColumns {
    
    @XmlElements(@XmlElement(name = "column"))
    private List<ColumnInfo> columns = new ArrayList<ColumnInfo>();

    public ListColumns() {
    }

    public ListColumns(List<ColumnInfo> columns) {
        this.columns = columns;
    }

    
    public List<ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnInfo> columns) {
        this.columns = columns;
    }
    
    
}
