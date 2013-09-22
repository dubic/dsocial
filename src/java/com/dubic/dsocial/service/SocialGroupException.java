/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.service;

/**
 *
 * @author user
 */
public class SocialGroupException extends Exception {
    public SocialGroupException(String msg){
        super(msg);
    }
    public SocialGroupException(String msg,Throwable cause){
        super(msg,cause);
    }
}
