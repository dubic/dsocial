/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.service;



import com.dubic.dsocial.models.Group;
import com.dubic.dsocial.models.User;
import com.dubic.dsocial.util.LinkUtils;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author dubic
 */
@Named
public class Search {
    public static String SECTION_ORGANIZATION = "ORGANIZATION";
    public static String SECTION_SERVICES = "SERVICES";
    public static String SECTION_PEOPLE = "PEOPLE";
    @PersistenceContext private EntityManager em;
    @Inject UserHandler uh;
    @Inject GroupHandler gh;
    @Inject LinkUtils lnk;
    private Logger log = Logger.getLogger(getClass());
    
    public Search(){
    }

//    public List<SearchResult> dashBoardSearch(String query, String userName) {
//        List<SearchResult> slist = new ArrayList<SearchResult>();
//        //Search for all TIA
//        List<Organization> tias = gh.findOrgsByQuery(query);
//        log.info("orgs >> "+tias.size());
//        for (Organization o : tias) {
//            slist.add(new SearchResult(lnk.getTiaLogoURL(o), o.getName(),o.getDescription() , null, SECTION_ORGANIZATION));
//        }
//        //search for services that are public
//        List<Service> services = gh.findPublicServicesByQuery(query);
//        log.info("services >> "+services.size());
//        for (Service s : services) {
//            slist.add(new SearchResult(lnk.getServiceLogoURL(s), s.getDisplayName(),s.getDescription(), null, SECTION_SERVICES));
//        }
//        //search for Users
//        List<User> users = findUsersSameTia(query,userName);
//         log.info("users >> "+users.size());
//        for (User u : users) {
//            slist.add(new SearchResult(lnk.getAvatarURL(u), u.getFullName(),null, null, SECTION_PEOPLE));
//        }
//        return slist;
//    }

    public List<User> findUsersSameTia(String query,String userName) {
//        em.createQuery("SELECT r.igroup FROM GroupRelationship r WHERE r.user.userId = :userName AND r.igroup.type = :tiagrp");// - selects connected groups
        return em.createQuery("SELECT u FROM IUser u WHERE u.firstname LIKE '%"+query+"%' OR u.lastname LIKE '%"+query+"%' AND "
                + " u.userId IN (SELECT r.user.userId FROM GroupRelationship r  WHERE r.igroup.id IN (SELECT rr.igroup.id FROM GroupRelationship rr WHERE rr.user.userId = :userName AND rr.igroup.type = :tiagrp))", User.class)
                .setParameter("userName", userName).setParameter("tiagrp", Group.Type.TIA).getResultList();
    }
    
    /**
     * checks if the service exists with the specified organization
     *
     * @param portalName the TIA portal name
     * @param serviceCode the service code
     * @return a <tt>Service</tt> or null if no service exists
     */
//    public Service findServiceByOrg(String portalName, String serviceCode) {
//        try {
//            return em.createQuery("SELECT s FROM Service s WHERE s.code = :code and s.organization.portalName = :portalName", Service.class)
//                    .setParameter("code", serviceCode).setParameter("portalName", portalName).getSingleResult();
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
