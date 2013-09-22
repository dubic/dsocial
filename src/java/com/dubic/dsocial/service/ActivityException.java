/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.service;

/**
 *
 * @author dubic
 */
public class ActivityException extends Exception {
    public ActivityException(String msg){
        super(msg);
    }
    public ActivityException(String msg,Throwable t){
        super(msg,t);
    }
}
