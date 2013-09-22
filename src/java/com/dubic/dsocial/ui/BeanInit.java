/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.ui;

import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
 * @author DUBIC
 */
@Named
public class BeanInit implements BeanPostProcessor{
private Logger log = Logger.getLogger(getClass());
    @Override
    public Object postProcessBeforeInitialization(Object o, String string) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String string) throws BeansException {
        if(bean instanceof Users){
            log.info("INIT USERS BEAN");
            Users u = (Users) bean;
            u.loadUsers();
        }
        return bean;
    }
    
}
