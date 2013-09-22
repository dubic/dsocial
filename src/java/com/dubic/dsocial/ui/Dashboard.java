/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.ui;

import com.dubic.dsocial.models.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import org.apache.log4j.Logger;

/**
 *
 * @author DUBIC
 */
@Named("dashboard")
@RequestScoped
public class Dashboard {
    private Logger log = Logger.getLogger(getClass());
    
    private List<User> userList = new ArrayList<User>();
    
    public Dashboard(){
        userList.add(new User("dubic", User.Gender.MALE, "ghgf", "hghg", "hfgfgh"));
        userList.add(new User("dcam", User.Gender.MALE, "uzu", "hghg", "hfgfgh"));
        
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
}
