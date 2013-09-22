/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.ui;

import com.dubic.dsocial.dao.exceptions.NonexistentEntityException;
import com.dubic.dsocial.models.User;
import com.dubic.dsocial.service.UserHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.inputtext.InputText;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author DUBIC
 */
@Named(value = "social")
@Scope(value="session")
public class Session {
    @Inject private UserHandler uh;
    private User user = new User();
    private boolean authenticated = false;
    private InputText userTxt;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InputText getUserTxt() {
        return userTxt;
    }

    public void setUserTxt(InputText userTxt) {
        this.userTxt = userTxt;
    }

    

    public String login() {
        try {
            user = uh.findUser(userTxt.getValue().toString());
            authenticated = true;
        } catch (NonexistentEntityException ex) {
             FacesMessage msg = new FacesMessage("username is incorrect");
       FacesContext.getCurrentInstance().addMessage("loginMsg", msg);
        }
        return "home.xhtml";
    }
}
