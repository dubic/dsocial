/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.dto;

import com.dubic.dsocial.models.User;
import com.esofties.jcrest.isocial.dto.ITia;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author dubic
 */
public class IUser {
    private String userId;
    private String uniqueName;
    private String fullName;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String email;
    private int friends;
    private int groups;
    private int requests;
    
//    private List<IUser> friends = new ArrayList<IUser>();
//    private List<ITia> connectedTIAs = new ArrayList<ITia>();
//    private StreamedContent image;
    
    private boolean online = false;
    
    public IUser(){
    }

    public IUser(String uniqueName, String fullName, String avatarUrl) {
        this.uniqueName = uniqueName;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
    }

    public IUser(User dbUser,String av) {
        this.userId = dbUser.getUserId();
        this.firstName = dbUser.getFirstname();
        this.lastName = dbUser.getLastname();
        this.fullName = dbUser.getFullName();
        this.uniqueName = dbUser.getUserId();
        this.email = dbUser.getEmail();
        this.avatarUrl = av;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public int getGroups() {
        return groups;
    }

    public void setGroups(int groups) {
        this.groups = groups;
    }

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

 

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "IUser{" + "uniqueName=" + uniqueName + ", fullName=" + fullName + ", firstName=" + firstName + ", lastName=" + lastName + ", avatarUrl=" + avatarUrl + ", email=" + email + ", online=" + online + '}';
    }

    
    
    
}
