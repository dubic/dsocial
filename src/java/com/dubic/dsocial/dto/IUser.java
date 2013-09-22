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
    private String uniqueName;
    private String fullName;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String email;
    private List<IUser> friends = new ArrayList<IUser>();
    private List<ITia> connectedTIAs = new ArrayList<ITia>();
    private StreamedContent image;
    
    private boolean online = false;
    
    public IUser(){
    }

    public IUser(String uniqueName, String fullName, String avatarUrl) {
        this.uniqueName = uniqueName;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
    }

    public IUser(User dbUser,String av) {
        this.firstName = dbUser.getFirstname();
        this.lastName = dbUser.getLastname();
        this.fullName = dbUser.getFullName();
        this.uniqueName = dbUser.getUserId();
        this.email = dbUser.getEmail();
        this.avatarUrl = av;
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

    public List<IUser> getFriends() {
        return friends;
    }

    public void setFriends(List<IUser> friends) {
        this.friends = friends;
    }

    public List<ITia> getConnectedTIAs() {
        return connectedTIAs;
    }

    public void setConnectedTIAs(List<ITia> connectedTIAs) {
        this.connectedTIAs = connectedTIAs;
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

    public StreamedContent getImage() {
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }
    
    
    
}
