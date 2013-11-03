/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.ui;

import com.dubic.dsocial.dao.exceptions.NonexistentEntityException;
import com.dubic.dsocial.dto.IUser;
import com.dubic.dsocial.models.Relationship;
import com.dubic.dsocial.models.User;
import com.dubic.dsocial.service.RelationshipHandler;
import com.dubic.dsocial.service.UserHandler;
import com.dubic.dsocial.util.LinkUtils;

import com.dubic.dsocial.util.MessageUtils;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author DUBIC
 */
@Named(value = "social")
@Scope(value="session")
public class Session {
    @Inject private UserHandler uh;
    @Inject private RelationshipHandler rh;
    
    @Inject private LinkUtils lnk;
    private IUser user = new IUser();
    private boolean authenticated = false;
    private InputText userTxt;
    private IUser selectedUser;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public InputText getUserTxt() {
        return userTxt;
    }

    public void setUserTxt(InputText userTxt) {
        this.userTxt = userTxt;
    }
public IUser getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(IUser selectedUser) {
        System.out.println("SELECTEC USER CALLED");
        this.selectedUser = selectedUser;
        System.out.println("user - "+selectedUser);
    }
    public void editUserdetails(AjaxBehaviorEvent evt){
        try {
            User u = uh.findUser(user.getUserId());
            u.setEmail(user.getEmail());
            u.setFirstname(user.getFirstName());
            u.setLastname(user.getLastName());
            u.setUpdated(new Date());
            uh.updateUser(u);
            MessageUtils.addMessage("editout", FacesMessage.SEVERITY_INFO, "details updated");
        } catch (NonexistentEntityException ex) {
            MessageUtils.addMessage("editout", FacesMessage.SEVERITY_ERROR, "error updating details");
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String login() {
        try {
           User u = uh.findUser(userTxt.getValue().toString());
           user = new IUser(u, lnk.getAvatarURL(u));
           
           user.setFriends(rh.getConnections(u).size());
           user.setRequests(rh.getRelationshipLevel(u.getUserId(), Relationship.LEVEL.PENDING).size());
            authenticated = true;
        } catch (NonexistentEntityException ex) {
             FacesMessage msg = new FacesMessage("username is incorrect");
       FacesContext.getCurrentInstance().addMessage("loginMsg", msg);
        }
        return "home.xhtml";
    }
    
    public String logout(){
        this.user = null;
        this.authenticated = false;
        return "home.xhtml";
    } 
    
    
    public void changePic(FileUploadEvent evt){
        System.out.println("name - "+evt.getFile().getFileName());
        
        try {
            //save img bytes in avatar
            User u = uh.changeProfilePic(evt.getFile(), this.user);
            MessageUtils.addMessage("uploadout", FacesMessage.SEVERITY_INFO, "Profile picture updated successfully");
            this.user = new IUser(u, lnk.getAvatarURL(u));
        } catch (NonexistentEntityException ex) {
            MessageUtils.addMessage("uploadout", FacesMessage.SEVERITY_ERROR, "user not found");
        }
    }
    
}
