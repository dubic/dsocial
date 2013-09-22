/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.service;

import com.dubic.dsocial.dao.exceptions.NonexistentEntityException;
import com.dubic.dsocial.models.Group;
import com.dubic.dsocial.models.IsocialActivity;
import com.dubic.dsocial.models.User;
import com.dubic.dsocial.util.LinkUtils;
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
public class ActivityHandler {

    @PersistenceContext private EntityManager em;
    @Inject private UserHandler uh;
    @Inject private GroupHandler gh;
    @Inject private LinkUtils lnk;
    private Logger log = Logger.getLogger(getClass());

    public ActivityHandler() {
    }


    public IsocialActivity saveComment(String userid, IsocialActivity activity, IsocialActivity comment) throws NonexistentEntityException, ActivityException, Exception {
        User user = uh.findUser(userid);
        if (comment == null) {
            throw new ActivityException("comment is null");
        }
        comment.setUser(user);
        comment.setComment(true);
        comment.setUpdated(new Date());
        comment.setType(IsocialActivity.Type.USER);
        comment.setStreamFaviconUrl(lnk.getAvatarURL(user));
        activity.getComments().add(comment);
        log.info("saving comment for activity " + activity.getActivityId());
        try {
            return updateActivity(activity);
        } catch (Exception e) {
            throw new Exception("error occurred in saving comment to activity", e);
        }
    }

    public List<IsocialActivity> getUserActivities(String userid, int start, int len) throws NonexistentEntityException {
        User user = uh.findUser(userid);
        List<IsocialActivity> aList = em.createNamedQuery("Activity.getByUser", IsocialActivity.class).setParameter("user", user)
                .setFirstResult(start).setMaxResults(len).getResultList();
        return aList;
    }

    public List<IsocialActivity> getUserConnectionsActivities(String userid, int start, int len) throws NonexistentEntityException {
        User user = uh.findUser(userid);
        List<IsocialActivity> aList = em.createNamedQuery("Activity.getActivityByUserAndFriends", IsocialActivity.class).setParameter("user", user)
                .setFirstResult(start).setMaxResults(len).getResultList();
        return aList;
    }
    
    public List<IsocialActivity> getUserActivityFeed(String userid, int start, int len) throws NonexistentEntityException {
        User user = uh.findUser(userid);
        List<IsocialActivity> aList = em.createNamedQuery("Activity.getFeed", IsocialActivity.class).setParameter("user", user)
                .setFirstResult(start).setMaxResults(len).getResultList();
        return aList;
    }
    
    public List<IsocialActivity> getGroupActivities(String userid, int start, int len) throws NonexistentEntityException{
        User user = uh.findUser(userid);
        return em.createQuery("SELECT a FROM IsocialActivity a WHERE a.igroup.id IN (SELECT g.igroup.id FROM GroupRelationship g WHERE g.user = :user) ORDER BY a.postedTime DESC", IsocialActivity.class)
                .setParameter("user", user).getResultList();
    }

//    @Transactional
    public IsocialActivity saveActivity(IsocialActivity a) {

        try {
            IsocialActivity merge = em.merge(a);
            em.flush();
            log.info(merge.getActivityId() + " activity created");
            return merge;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Transactional
    public IsocialActivity updateActivity(IsocialActivity a) {
        IsocialActivity merge = em.merge(a);
        log.info(merge.getActivityId() + " activity updated");
        return merge;
    }

    public IsocialActivity findActivity(Long activityId) throws ActivityException {
        IsocialActivity find = em.find(IsocialActivity.class, activityId);
        if(find == null) {
            throw new ActivityException("Activity is null");
        }
        return find;
    }

    public IsocialActivity postGroup(Group group, IsocialActivity.Type type, String post,String avatarUrl) throws Exception {
        IsocialActivity activity = new IsocialActivity();
        activity.setUser(null);
        activity.setType(type);
        activity.setComment(false);
        activity.setUpdated(new Date());
        activity.setIgroup(group);
        activity.setTitle(post);
        activity.setStreamFaviconUrl(avatarUrl);
        try {
            return saveActivity(activity);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new Exception("error occurred in creating user activity", e);
        }
    }
   
    public IsocialActivity postUser(String userId, String post) throws Exception {
        IsocialActivity activity = new IsocialActivity();
        User user = uh.findUser(userId);
        activity.setUser(user);
        activity.setType(IsocialActivity.Type.USER);
        activity.setComment(false);
        activity.setUpdated(new Date());
        activity.setIgroup(null);
        activity.setTitle(post);
       activity.setStreamFaviconUrl(lnk.getAvatarURL(user));
        try {
            return saveActivity(activity);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new Exception("error occurred in creating user activity", e);
        }
    }

//    public IsocialActivity createGroupActivity(IActivity a) throws Exception {
//        Group grp = null;
//        String avatarUrl = null;
//        if(a.getType().equalsIgnoreCase(IActivity.TYPE_SERVICE)){
//            Service service = gh.findServiceByCodeAndTia(a.getServiceCode(), a.getOwner());
//            grp = service.getIgroup();
//            avatarUrl = lnk.getServiceLogoURL(service);
//        }
//        else if(a.getType().equalsIgnoreCase(IActivity.TYPE_TIA)){
//            Organization org = gh.findOrgByName(a.getOwner());
//            grp = org.getIgroup();
//            avatarUrl = lnk.getTiaLogoURL(org);
//        }
//        if(grp == null){
//            throw new Exception("group is null");
//        }
//        return postGroup(grp, IsocialActivity.Type.SERVICE, a.getTitle(), avatarUrl);
//    }
    
    
   
}
