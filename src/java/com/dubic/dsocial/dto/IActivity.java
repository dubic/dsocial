/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esofties.jcrest.isocial.dto;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dubic
 */
public class IActivity implements Serializable{
    public static final String TYPE_USER = "user";
    public static final String TYPE_TIA = "tia";
    public static final String TYPE_SERVICE = "service";
    private Long id;
    private String avatarUrl;
    private String owner;
    private String title;
    private List<IComment> comments = new ArrayList<IComment>();
    private String postedTime;
    private boolean commentable = true;
    private boolean hidden = false;
    private String type;
    private String serviceCode;
    private List<String> likes = new ArrayList<String>();
    private List<String> dislikes = new ArrayList<String>();

    public IActivity(Long id, String owner, String title, String postedTime) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.postedTime = postedTime;
    }

    public IActivity(Long id, String title, String postedTime) {
        this.id = id;
        this.title = title;
        this.postedTime = postedTime;
    }
    
    
    public IActivity(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<IComment> getComments() {
        return comments;
    }

    public void setComments(List<IComment> comments) {
        this.comments = comments;
    }

    public String getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(String postedTime) {
        this.postedTime = postedTime;
    }

    public boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<String> dislikes) {
        this.dislikes = dislikes;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    

    public String tosString(){
       return new Gson().toJson(this);
    }
    
}
