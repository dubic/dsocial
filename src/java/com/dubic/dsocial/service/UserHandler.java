/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.service;


import com.dubic.dsocial.dao.exceptions.NonexistentEntityException;
import com.dubic.dsocial.models.User;
import com.dubic.dsocial.models.User.Gender;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dubic
 */
@Named("userHandler")
@Transactional
public class UserHandler {
    
    @PersistenceContext
    private EntityManager em;
    private Logger log = Logger.getLogger(getClass());

    public UserHandler() {
    }

    
    public User createUser(String username, Gender gender, String fname, String lname, String email) {
        User user = new User(username, gender, fname, lname, email);
        try {
            
            return saveUser(user);
        } catch (Exception e) {
            log.error("error creating social user");
            log.error(e.getMessage(), e.fillInStackTrace());
        }
        return null;
    }

    /////----------------------MAIN DAOs-------------------------------------//////////////////////////
//    @Transactional
    public User saveUser(User user) {
        log.info("persisting social user...");
        em.persist(user);
        log.info(user.getFullName() + " social user created");
        return user;
    }

//    @Transactional
    public User updateUser(User user) {
        log.info("updating social user...");
        User u2 = em.merge(user);
        log.info(u2.getFullName() + " social user updated");
        return u2;
    }

//    @Transactional
    public void deleteUser(String userid) {
        log.info("deleting social user...");
        User u = em.find(User.class, userid);
        em.remove(u);
        log.info(u.getFullName() + " social user deleted");
    }

    public List<User> findUsers() {
        log.debug("find all users");
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    /**
     *
     * @param userid the unique id of the user - username
     * @return the user or null if 
     */
    
    public User findUser(String userid) throws NonexistentEntityException {
        User u = em.find(User.class, userid);
        if (u == null) {
            throw new NonexistentEntityException(userid + " does not exist as a social User");
        }
        
        return u;
    }

    public User createUser(User user)throws Exception{
        User savedUser = saveUser(user);
        return user;
    }
    
}
