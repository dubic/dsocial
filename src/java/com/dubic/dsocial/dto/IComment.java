/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esofties.jcrest.isocial.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dubic
 */
public class IComment {
    private Long id;
    private String avatarUrl;
    private String userName;
    private String title;
    private String postedTime;
    private String activityId;
    private boolean hidden = false;
    private List<String> likes = new ArrayList<String>();
    private List<String> dislikes = new ArrayList<String>();
    
    public IComment(){
    }

    public IComment(Long id, String userName, String title, String postedTime, String activityId) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.postedTime = postedTime;
        this.activityId = activityId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(String postedTime) {
        this.postedTime = postedTime;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
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

    @Override
    public String toString() {
        return "IComment{" + "id=" + id + ", avatarUrl=" + avatarUrl + ", userName=" + userName + ", title=" + title + ", postedTime=" + new Date(postedTime) + ", activityId=" + activityId + ", hidden=" + hidden + ", likes=" + likes.size() + ", dislikes=" + dislikes.size() + '}';
    }
    
    
}
