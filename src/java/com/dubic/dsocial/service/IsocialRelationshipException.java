/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.service;

/**
 *
 * @author dubic
 */
public class IsocialRelationshipException extends Exception {
    public IsocialRelationshipException(String msg){
        super(msg);
    }
    public IsocialRelationshipException(String msg,Throwable cause){
        super(msg,cause);
    }
}
