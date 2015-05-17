/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.engine;

/**
 *
 * @author mauna
 * @param <I> - request type
 * @param <O> - response type
 */
public interface IDataEngine<I,O> {
    
    O get(I request);
    
    void put(I request);
    
    void update(I request);
    
    void remove(I request);
}
