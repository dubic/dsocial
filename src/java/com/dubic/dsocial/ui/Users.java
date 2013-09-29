/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.ui;

import com.dubic.dsocial.dto.IUser;
import com.dubic.dsocial.models.User;
import com.dubic.dsocial.service.UserHandler;
import com.dubic.dsocial.util.LinkUtils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
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
@Named("users")
@Scope(value="request")
public class Users {

    private Logger log = Logger.getLogger(getClass());
    private IUser iuser = new IUser();
    private User user = new User();
    private IUser selectedUser;
    private List<IUser> iuserList = new ArrayList<IUser>();
    @Inject
    private UserHandler uh;
    @Inject
    private LinkUtils lnk;

    public Users() {
        log.info("CONSTRUCTOR CALLED");
//        if(uh != null){
       
//        }
    }

    
    public void loadUsers() {
        log.info("INITIALIZING IMAGES....");
        List<User> userList = uh.findUsers();
        for (User u : userList) {
            IUser iUser = new IUser(u, lnk.getAvatarURL(u));
//            iUser.setImage(lnk.getAvatarURL(u));
//            iUser.setImage(lnk.testS());
            iuserList.add(iUser);
//            StreamedContent avatarURL = lnk.getAvatarURL(u);
//            log.info("IMAGE NAME - "+avatarURL.getName());
//            u.setImage(avatarURL);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IUser getIuser() {
        return iuser;
    }

    public void setIuser(IUser iuser) {
        this.iuser = iuser;
    }

    public List<IUser> getIuserList() {
        return iuserList;
    }

    public void setIuserList(List<IUser> iuserList) {
        this.iuserList = iuserList;
    }

    
    public void createUser() {
        log.debug(user.toString());

        try {
            User u = uh.createUser(user);
            FacesContext.getCurrentInstance().addMessage("userCreateMsg", new FacesMessage(u.getUserId() + " created"));
        } catch (Exception ex) {

            FacesContext.getCurrentInstance().addMessage("userCreateMsg", new FacesMessage(ex.getMessage()));
            log.error(ex);
            ex.printStackTrace();
        }
    }

    public IUser getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(IUser selectedUser) {
        System.out.println("SELECTEC USER CALLED");
        this.selectedUser = selectedUser;
        System.out.println("user - "+selectedUser);
    }
    

public void details(AjaxBehaviorEvent evt){
    System.out.println("event - "+evt.getComponent().getId());
    System.out.println("user - "+selectedUser);
}    
    
}
