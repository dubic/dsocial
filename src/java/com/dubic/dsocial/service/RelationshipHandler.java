/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.service;

import com.dubic.dsocial.dao.exceptions.NonexistentEntityException;
import com.dubic.dsocial.models.Relationship;
import com.dubic.dsocial.models.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
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
public class RelationshipHandler {

    public RelationshipHandler() {
    }
    @PersistenceContext
    private EntityManager em;
    @Inject UserHandler uh;
    @Inject ActivityHandler ah;
    private Logger log = Logger.getLogger(getClass());

    /**
     * requests a friend connection with another user who receives a pending
     * connection notification
     *
     * @param fromUser - the <tt>User</tt> model of the sender of the
     * relationship
     * @param toUser the <tt>User</tt> model of the receiver of the relationship
     * @param howKnow reason for the relationship
     * @throws NonexistentEntityException if the user ids do not exist
     * @throws IsocialRelationshipException if the relationship exists
     */
    
    public void requestRelationship(String fromUser, String toUser, String howKnow) throws NonexistentEntityException, IsocialRelationshipException, Exception {

        if (getRelationship(fromUser, toUser) != null) {
            throw new IsocialRelationshipException(" Relationship already exists");
        }
        try {
            User from = uh.findUser(fromUser);
            User to = uh.findUser(toUser);
            Relationship relationship = new Relationship(from, to, howKnow, Relationship.LEVEL.PENDING);
            log.info("requesting a relationship...");
            createRelationship(relationship);
        } catch (Exception e) {
            throw new Exception("error creating social relationship", e);
        }
    }

    /**
     * creates a relationship as PENDING model a waiting the confirmation of the
     * receiver
     *
     * @param fromUser - the <tt>User</tt> model of the sender of the
     * relationship
     * @param toUser the <tt>User</tt> model of the receiver of the relationship
     * @param howKnow reason for the relationship
     */
    public void requestRelationship(User from, User to, String howKnow) throws IsocialRelationshipException, Exception {
        if(from == to) {
            throw new IsocialRelationshipException("Illegal Relatioship connection");
        }
        Relationship relationship = new Relationship(from, to, howKnow, Relationship.LEVEL.PENDING);
        if (relationshipExists(relationship) != null) {
            throw new IsocialRelationshipException(relationship.getName() + " Relationship already exists");
        }
        try {
            log.info("requesting a relationship...");
            createRelationship(relationship);
        } catch (Exception e) {
            throw new Exception("error creating social relationship", e);
        }
    }

    /**
     * creates a relationship as ACCEPTED model without confirmation from
     * recipient
     *
     * @param fromUser - the userId of the sender of the relationship
     * @param toUser the userId of the receiver of the relationship
     * @param howKnow reason for the relationship
     * @throws NonexistentEntityException if either user ids is/are not found as
     * <tt>User</tt> models
     */
    public void makeNewRelationship(String fromUser, String toUser, String howKnow,boolean broadcast) throws NonexistentEntityException, IsocialRelationshipException, Exception {
        if(fromUser == null ? toUser == null : fromUser.equals(toUser)) {
            throw new IsocialRelationshipException("Illegal Relatioship connection");
        }
        if (getRelationship(fromUser, toUser) != null) {
            throw new IsocialRelationshipException("Relationship already exists");
        }
        try {
            User from = uh.findUser(fromUser);
            User to = uh.findUser(toUser);
            Relationship relationship = new Relationship(from, to, howKnow, Relationship.LEVEL.ACCEPTED);
            log.info("creating relationship as ACCEPTED");
            createRelationship(relationship);
            postActivity(from,to,broadcast);
        } catch (Exception e) {
             throw new Exception("error creating social relationship", e);
        }
    }

    /**
     * call to accept a pending relationship
     *
     * @param relationship
     * @return the updated relationship
     */
    public Relationship acceptRelationship(Relationship relationship, boolean broadcast) {
        relationship.setLevel(Relationship.LEVEL.ACCEPTED);
        relationship.setUpdated(new Date());
        Relationship updatedRelationship = updateRelationship(relationship);
        log.info("relationship accepted");
        postActivity(updatedRelationship.getFrom(),updatedRelationship.getTo(),broadcast);
        return updatedRelationship;
    }

    public Relationship ignoreRelationship(Relationship relationship) {
        relationship.setLevel(Relationship.LEVEL.IGNORED);
        relationship.setUpdated(new Date());
        Relationship updatedRelationship = updateRelationship(relationship);
        log.info("relationship ignored");
        return updatedRelationship;
    }

    public Relationship rejectRelationship(Relationship relationship) {
        relationship.setLevel(Relationship.LEVEL.REJECTED);
        relationship.setUpdated(new Date());
        Relationship updatedRelationship = updateRelationship(relationship);
        log.info("relationship rejected");
        return updatedRelationship;
    }

    /**
     * @param user the user
     * @return list of <tt>User</tt> pending user connections awaiting confirmation
     */
    public List<User> getRelationshipLevel(User user,Relationship.LEVEL level) {
        List<Relationship> resultList = em.createNamedQuery("Relationship.find.level.to", Relationship.class).setParameter("level", level).setParameter("user", user)
                .getResultList();
        return getUsersInRelationships(resultList, user);
    }
    /**
     * @param user the user
     * @return list of <tt>User</tt> already connected to the specified user
     */
    public List<User> getConnections(User user){
        List<Relationship> resultList = em.createNamedQuery("Relationship.find.level.from.to", Relationship.class).setParameter("level", Relationship.LEVEL.ACCEPTED).setParameter("user", user)
                .getResultList();
        return getUsersInRelationships(resultList, user);
    }
    
    
///////////////----------------------------------------------------------------------------------------------------------------------------------------------------------////////////////////
    
    private void createRelationship(Relationship relationship) {
        em.persist(relationship);
        log.info(relationship.getName() + " relationship created with level " + relationship.getLevel().toString());
    }

//    @Transactional
    public Relationship updateRelationship(Relationship relationship) {
        Relationship merge = em.merge(relationship);
        log.info(merge.getName() + " relationship updated with level " + merge.getLevel().toString());
        return merge;
    }

//    @Transactional
    public void removeRelationship(Long id) {
        Relationship r = em.find(Relationship.class, id);
        em.remove(r);
        log.info(r.getName() + " relationship deleted");
    }
///////////////////////////////////////-----------------------------------------------------------------------------------////////////////////////////////////////////////////////
    public Relationship relationshipExists(Relationship relationship) {
        User from = relationship.getFrom();
        User to = relationship.getTo();
        //        LEVEL level = relationship.getLevel();
        try {
            Relationship r = em.createNamedQuery("Relationship.exists", Relationship.class)
                    .setParameter("fromId", from.getUserId()).setParameter("toId", to.getUserId()).getSingleResult();
            return r;
        } catch (Exception e) {
//            log.warn("No Relationship found");
        }
        return null;
    }

    
    public Relationship getRelationship(String user1, String user2) {
        try {
            Relationship r = em.createNamedQuery("Relationship.exists", Relationship.class)
                    .setParameter("fromId", user1).setParameter("toId", user2).getSingleResult();
            return r;
        } catch (Exception e) {
//            log.warn("No Relationship found");
        }
        return null;
    }
    
    private List<User> getUsersInRelationships(List<Relationship> resultList, User user){
        List<User> userList = new ArrayList<User>();
        for (Relationship r : resultList) {
            if (!r.getFrom().equals(user)) {
                userList.add(r.getFrom());
                continue;
            }
            if (!r.getTo().equals(user)) {
                userList.add(r.getTo());
                continue;
            }
        }
        return userList;
    }

    private void postActivity(User from, User to, boolean broadcast) {
//        if(broadcast){
//            ah.postUser(null, null)
//        }
    }

}
