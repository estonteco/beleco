/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory.serializers;

import com.estonteco.spark.frames.conf.IDataFrameConf;
import com.estonteco.spark.frames.conf.impl.DefaultFrameConf;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author APolokh
 */
public class XMLConfSerializer {

    private static JAXBContext jaxbContext;
            
    static{
        try {
            jaxbContext = JAXBContext.newInstance(DefaultFrameConf.class);
        } catch (JAXBException ex) {
            throw new RuntimeException("JAXBContext initialization failed", ex);
        }  
    }
    
    
    public static byte[] serialize(IDataFrameConf conf) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(conf, baos);
        } catch (JAXBException ex) {
            throw new RuntimeException("Conf serialization failed", ex);
        }
        return baos.toByteArray();
    }

    
     public static IDataFrameConf deserialize(InputStream inputStream) {
        try {
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (IDataFrameConf) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException ex) {
            throw new RuntimeException("Conf deserialization failed", ex);
        }
    }

}
