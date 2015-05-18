/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.excel;

import com.estonteco.spark.frames.conf.IDataFrameConf;
import com.estonteco.spark.frames.conf.factory.serializers.StructTypeJAXBAdapter;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.spark.sql.types.StructType;

/**
 *
 * @author mauna
 */
@XmlRootElement(name = "conf")
@XmlType(propOrder={"name", "type", "URL", "schema","properties"})
public class ExcelFrameConf implements IDataFrameConf {

    private String name;
    private String type;
    private String URL;
    private StructType schema;
    private Map<String,String> properties;

    public ExcelFrameConf() {
    }

    public ExcelFrameConf(String name, String type, String URL, StructType schema, Map properties) {
        this.name = name;
        this.type = type;
        this.URL = URL;
        this.schema = schema;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

   
    public StructType getSchema() {
        return schema;
    }

    @XmlElement
    @XmlJavaTypeAdapter(type = StructType.class, value = StructTypeJAXBAdapter.class)
    public void setSchema(StructType schema) {
        this.schema = schema;
    }

    public Map<String,String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String,String> properties) {
        this.properties = properties;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExcelFrameConf other = (ExcelFrameConf) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        return true;
    }

    
}
