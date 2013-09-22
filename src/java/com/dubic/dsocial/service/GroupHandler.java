/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.service;

import com.dubic.dsocial.dao.exceptions.NonexistentEntityException;
import com.dubic.dsocial.models.Group;
import com.dubic.dsocial.models.GroupRelationship;
import com.dubic.dsocial.models.IsocialActivity;
import com.dubic.dsocial.models.Organization;
import com.dubic.dsocial.models.Relationship;
import com.dubic.dsocial.models.Relationship.LEVEL;
import com.dubic.dsocial.models.User;
import com.dubic.dsocial.util.LinkUtils;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXB;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dubic
 */
@Named
@Transactional

public class GroupHandler {

    @PersistenceContext private EntityManager em;
    @Inject private UserHandler uh;
    @Inject private ActivityHandler ah;
    @Inject private Search search;
    @Inject private LinkUtils lnk;
    private Logger log = Logger.getLogger(getClass());

    public GroupHandler() {
    }
    /////////////////////////////////////////////////////////////////////////////////////////////ORGANIZATION HANDLING/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * creates an organization model and a default group if none attached.adds a
     * the group creator to the group as ACCEPTED. saves the list of services in
     * the org if attached
     *
     * @param org the organization model to save.
     * @throws SocialGroupException if JPA errors occur
     */
    public Organization createOrganization(Organization org,boolean broadcast) throws Exception {
        if (org.getIgroup() == null) {
            Group g = new Group(org.getPortalName(), null, org.getCreator(), Group.Type.TIA);
            org.setIgroup(g);
        }
        log.info("persisting organization and correponding group...");
        Organization savedOrg = save(org);
        if(broadcast) {
            ResourceBundle b = ResourceBundle.getBundle("resources.conf");
            String msg = MessageFormat.format(b.getString("activity.organization.creation"), savedOrg.getPortalName(), savedOrg.getCreator().getFullName());
            ah.postGroup(savedOrg.getIgroup(),IsocialActivity.Type.TIA, msg,lnk.getTiaLogoURL(savedOrg));
            log.info("TIA creation activity posted");
        }
        log.info("organization persisted with name - " + savedOrg.getName());
        addUserToGroup(savedOrg.getIgroup(), savedOrg.getCreator(), GroupRelationship.RelationshipType.FOUNDER ,broadcast);
        
//        JAXB.marshal(savedOrg, System.out);
        return savedOrg;
    }
    
public Organization findOrgByName(String portalName) throws Exception{
    try {
            return em.createNamedQuery("Organization.find.portal", Organization.class).setParameter("portalName", portalName).getSingleResult();
        } catch (Exception e) {
            throw new Exception(e);
        }
}

public List<Organization> findOrgs(){
    return em.createNamedQuery("Organization.find.all", Organization.class).getResultList();
}
    /////////////////////////////////////////////////////////////////////////////////////////////SERVICE HANDLING/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public void createService(Service service, boolean broadcast) throws SocialGroupException, Exception {
//        Service s = search.findServiceByOrg(service.getOrganization().getPortalName(), service.getCode());
//        if (s != null) {
//            throw new SocialGroupException("Service " + s.getDisplayName()+ " already exists for " + s.getOrganization().getPortalName());
//        }
//        if (service.getIgroup() == null) {
//            Group g = new Group(service.getName(), service.getDescription(), service.getCreator(), Group.Type.SERVICE);
//            service.setIgroup(g);
//        }
//        log.info("persisting service and correponding group...");
//        Service save = save(service);
//        log.info("service persisted with id " + save.getId());
//        if(broadcast) {
//            ResourceBundle b = ResourceBundle.getBundle("resources.conf");
//            String msg = MessageFormat.format(b.getString("activity.service.creation"), save.getDisplayName(), save.getCreator().getFullName());
//            ah.postGroup(save.getIgroup(),IsocialActivity.Type.SERVICE, msg,lnk.getServiceLogoURL(save));
//            log.info("Service creation activity posted");
//        }
////        JAXB.marshal(save, System.out);
//        addUserToGroup(save.getIgroup(), save.getCreator(), GroupRelationship.RelationshipType.FOUNDER, broadcast);
//    }
    
//    public void updateService(Service s, boolean broadcast) throws Exception {
//        Service save = save(s);
//        if(broadcast) {
//            ResourceBundle b = ResourceBundle.getBundle("resources.conf");
//            String msg = MessageFormat.format(b.getString("activity.service.publish"), save.getDisplayName());
//            ah.postGroup(save.getIgroup(),IsocialActivity.Type.SERVICE, msg,lnk.getServiceLogoURL(save));
//            log.info("Service update activity posted");
//        }
//    }
    
//    public void addJMAToService(String username,String serviceCode,String portalName,String serviceName,boolean broadcast ) throws Exception{
//        User user = uh.findUser(username);
//            log.info("isocial - Adding JMA to TIA Service...");
//            Service service = findServiceByCodeAndTia(serviceCode, portalName);
//            addUserToGroup(service.getIgroup(), user, GroupRelationship.RelationshipType.MEMBER, broadcast);
//            log.info("isocial - JMA added to Service group");
//            
//            ResourceBundle b = ResourceBundle.getBundle("resources.conf");
//            
//            String msg = MessageFormat.format(b.getString("activity.service.jma.add"), user.getFullName(), service.getDisplayName());
//            if (service.getCode().equalsIgnoreCase(b.getString("isocial.service.code.is")) && serviceName == null) {
//                msg = MessageFormat.format(b.getString("activity.service.jma.add.IS"), user.getFullName(), portalName);
//            }
//            ah.postGroup(service.getIgroup(), IsocialActivity.Type.SERVICE, msg, lnk.getServiceLogoURL(service));
//            log.info("add jma to service activity posted");
//    }
    
//    public void addJMAToTIA(String username,String portalName,boolean broadcast) throws Exception{
//         User user = uh.findUser(username);
//        Organization org = findOrgByName(portalName);
//            addUserToGroup(org.getIgroup(), user, GroupRelationship.RelationshipType.MEMBER, broadcast);
//            log.info("isocial - JMA added to TIA Group");
//    }
//    
    
//    public List<Service> findOrgServices(String portalName){
//        return em.createQuery("SELECT s FROM Service s WHERE s.organization.portalName = :portalName", Service.class)
//                    .setParameter("portalName", portalName).getResultList();
//    }
/////////////////////////////////////////////////////////////////////////////////////////////GROUP HANDLING/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void createGroup(Group g, boolean broadcast) throws Exception {
        log.info("persisting Group...");
        Group save = save(g);
        log.info("Group persisted with id " + save.getId());
        if(broadcast) {
            ah.postGroup(save,IsocialActivity.Type.GROUP,save.getName()+" group created by "+save.getCreator().getFullName(),lnk.getGroupLogo(save));
            log.info("Group creation activity posted");
        }
        JAXB.marshal(save, System.out);
        addUserToGroup(save, save.getCreator(),GroupRelationship.RelationshipType.FOUNDER,broadcast);
    }
/////////////////////////////////////////////////////////////////////////////////////////////GROUP RELATIONSHIPS HANDLING/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * creates a group relationship with the user with relationship level as
     * ACCEPTED
     *
     * @param g the specified group
     * @param u the user to add to the group
     */
    public GroupRelationship addUserToGroup(Group g, User u,GroupRelationship.RelationshipType type,boolean broadcast) throws Exception {//check
        log.info("persisting Group relationship...");
        if(findGroupRelationship(g, u) != null){
           log.info("group relationship exists can't create a new");
            //throw new SocialGroupException("group relationship exists can't create a new");
           return null;
        }
        GroupRelationship gr = new GroupRelationship(g, u, false);
        gr.setLevel(Relationship.LEVEL.ACCEPTED);
        gr.setMemberType(type);

        GroupRelationship save = save(gr);
        log.info("GroupRelationship persisted with id " + save.getId());
        if(broadcast) {
            ResourceBundle b = ResourceBundle.getBundle("resources.conf");
            Group grp = gr.getIgroup();
            String msg;
            
            if(grp.getType() == Group.Type.SERVICE){
                msg = MessageFormat.format(b.getString("activity.servicegroup.user.add"), u.getFullName(), grp.getName());
                ah.postGroup(grp,IsocialActivity.Type.SERVICE,msg,lnk.getGroupLogo(grp));
            }
            else if(grp.getType() == Group.Type.TIA){
                msg = MessageFormat.format(b.getString("activity.tiagroup.user.add"), u.getFullName(), grp.getName());
                ah.postGroup(grp,IsocialActivity.Type.TIA,msg,lnk.getGroupLogo(grp));
            }
            else if(grp.getType() == Group.Type.GROUP){
                msg = MessageFormat.format(b.getString("activity.group.user.add"), u.getFullName(), grp.getName());
                ah.postGroup(grp,IsocialActivity.Type.GROUP,msg,lnk.getGroupLogo(grp));
            }     
            log.info("group user addition activity posted");
        }
        return save;
    }
/////////////////////////////////////////////////////////////////////////////////////////////CRUD/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private <T> T save(T t) {
        return em.merge(t);
    }

    private <T> void delete(Class<T> tclass, T t) {
        T find = em.find(tclass, t);
        em.remove(find);
        log.info("social group deleted " + t);
    }

    public List<Organization> getTIAGroups(String uname) throws NonexistentEntityException {
//        User user = uh.findUser(uname);
       // SELECT ALL ORGS WHERE USER IS IN GROUP RELATIONSHIP WITH ORG
        return em.createQuery("SELECT o FROM Organization o WHERE o.igroup.id IN (SELECT g.id FROM IGroup g WHERE g.id IN (SELECT r.igroup.id FROM GroupRelationship r WHERE r.user.userId = :user and r.level = :level ) ) ", Organization.class)
                .setParameter("user", uname).setParameter("level", LEVEL.ACCEPTED).getResultList();
    }

    public List<Organization> findOrgsByQuery(String query) {
       return em.createQuery("SELECT o FROM Organization o WHERE o.portalName LIKE '%"+query+"%'", Organization.class).getResultList();
    }

//    public List<Service> findPublicServicesByQuery(String query) {
//         return em.createQuery("SELECT s FROM Service s WHERE s.name LIKE '%"+query+"%' AND s.visibility = :visb",Service.class).setParameter("visb", Visibility.PUBLIC).getResultList();
//    }
    
    public GroupRelationship findGroupRelationship(Group g, User u){
        try {
            return (GroupRelationship) em.createQuery("SELECT r FROM GroupRelationship r WHERE r.igroup = :group AND r.user = :user").setParameter("group", g).setParameter("user", u).getSingleResult();
        } catch (Exception e) {
            log.debug("group relationship does not exist. create new..");
            return null;
        }
    }

//    public Service findServiceByCodeAndTia(String serviceCode, String portalName) throws Exception {
//        try {
//            return em.createQuery("SELECT s FROM Service s WHERE s.code = :code AND s.organization.portalName = :portalName", Service.class)
//                    .setParameter("code", serviceCode).setParameter("portalName", portalName).getSingleResult();
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }

//    public List<Service> findUserServicesInOrg(Organization org, User user){
//        return em.createQuery("SELECT s FROM Service s WHERE s.organization = :org AND s.igroup.id IN (SELECT r.igroup.id FROM GroupRelationship r WHERE r.user = :user)", Service.class)
//                .setParameter("org", org).setParameter("user", user).getResultList();
//    }
    
}
