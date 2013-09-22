/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.service;


import com.dubic.dsocial.dao.exceptions.NonexistentEntityException;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dubic
 */
@Named

@Transactional
public class Database {
    @PersistenceContext private EntityManager em;
    private Logger log = Logger.getLogger(getClass());
    
    public Database(){
    }
    
    public <T> void create(T t){
        em.persist(t);
        log.debug("create Method executed");
    }
    
    public <T> T createFlush(T t){
        em.persist(t);
        em.flush();
        log.debug("createFlush Method executed");
        return t;
    }
    
    public <T> T edit(T t){
        return em.merge(t);
    }
    
    public <T> void delete(T t){
        em.remove(t);
        log.debug("delete Method executed");
    } 
    
    public <T> T find(Class<T> type, Object o) throws NonexistentEntityException{
        T find = em.find(type, o);
        if(find == null) {
            throw new NonexistentEntityException("entity does not exist "+find.toString());
        }
        return find;
    }
}
