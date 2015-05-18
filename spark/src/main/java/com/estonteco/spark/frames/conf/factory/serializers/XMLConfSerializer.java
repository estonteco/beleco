/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory.serializers;

import com.estonteco.spark.frames.conf.IDataFrameConf;
import com.estonteco.spark.frames.excel.ExcelFrameConf;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.spark.sql.types.StructType;

/**
 *
 * @author APolokh
 */
public class XMLConfSerializer {

    public static byte[] serialize(IDataFrameConf conf) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(conf.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(jaxbMarshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(conf, baos);
        } catch (JAXBException ex) {
            throw new RuntimeException("Conf serialization failed", ex);
        }
        return baos.toByteArray();
    }

    public static IDataFrameConf deserialize(InputStream inputStream, 
            Class<? extends IDataFrameConf> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (IDataFrameConf) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException ex) {
            throw new RuntimeException("Conf deserialization failed", ex);
        }
    }

}
