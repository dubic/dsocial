/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.ui;

import com.dubic.dsocial.dao.exceptions.NonexistentEntityException;
import com.dubic.dsocial.dto.IRelationship;
import com.dubic.dsocial.dto.IUser;
import com.dubic.dsocial.models.Relationship;
import com.dubic.dsocial.models.User;
import com.dubic.dsocial.service.RelationshipHandler;
import com.dubic.dsocial.util.LinkUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author DUBIC
 */
@Named("dashboard")
@Scope(value="request")
public class Dashboard {
    private Logger log = Logger.getLogger(getClass());
    @Inject private RelationshipHandler rh;
    @Inject private Session session;
    @Inject private LinkUtils lnk;
    private String relationshipId = "relationshipId";
    private List<IRelationship> friends = new ArrayList<IRelationship>();
    private List<IRelationship> friendRequests = new ArrayList<IRelationship>();
    
    public Dashboard(){
        
    }

    public List<IRelationship> getFriends() {
        return friends;
    }

    public void setFriends(List<IRelationship> friends) {
        this.friends = friends;
    }

    public List<IRelationship> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(List<IRelationship> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public String getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(String relationshipId) {
        this.relationshipId = relationshipId;
    }
    

    public void getFriendList(){
            List<Relationship> relList = rh.getPendingRequestsl(session.getUser().getUserId());
            for (Relationship relationship : relList) {
                friendRequests.add(new IRelationship(relationship, lnk.getAvatarURL(relationship.getFrom())));
            }
    }
    
    public void acceptFriendRequest(AjaxBehaviorEvent evt){
        System.out.println("acceptFriendRequest{}");
        HtmlCommandButton acceptBtn = (HtmlCommandButton) evt.getComponent();
        Object value = acceptBtn.getValueExpression(relationshipId).getValue(FacesContext.getCurrentInstance().getELContext());
        Long relIdvalue = (Long) value;
        log.info("attr val = "+relIdvalue);
//        try {
//            rh.acceptRelationship(relIdvalue, true);
//        } catch (NonexistentEntityException ex) {
//            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
