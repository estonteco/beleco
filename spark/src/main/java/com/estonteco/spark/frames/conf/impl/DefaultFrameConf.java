/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.impl;

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
 * @author APolokh
 */

@XmlRootElement(name = "conf")
@XmlType(propOrder={"name", "type", "schema", "properties"})
public class DefaultFrameConf implements IDataFrameConf {

    protected String name;
    protected String type;
    protected StructType schema;
    protected Map<String, String> properties;

    public DefaultFrameConf() {
    }

    public DefaultFrameConf(String name, String type, StructType schema, Map<String, String> properties) {
        this.name = name;
        this.type = type;
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


    public StructType getSchema() {
        return schema;
    }

   
    @XmlElement
    @XmlJavaTypeAdapter(type = StructType.class, value = StructTypeJAXBAdapter.class)
    @Override
    public void setSchema(StructType schema) {
        this.schema = schema;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
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
        final DefaultFrameConf other = (DefaultFrameConf) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        return true;
    }
}
