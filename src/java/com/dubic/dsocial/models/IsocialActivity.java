/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.models;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.shindig.social.opensocial.model.Activity;
import org.apache.shindig.social.opensocial.model.MediaItem;

/**
 *
 * @author dubic
 */
@Entity
@Table(name="tbl_isocial_activity")
@NamedQueries({
    @NamedQuery(name="Activity.getAll",query="SELECT a FROM IsocialActivity a ORDER BY a.postedTime DESC"),
    @NamedQuery(name="Activity.getByUser",query="SELECT a FROM IsocialActivity a WHERE a.user = :user "),
    @NamedQuery(name="Activity.getActivityByUserAndFriends",query="SELECT a FROM IsocialActivity a WHERE a.user = :user OR a.user.userId IN (SELECT rr.from.userId FROM Relationship rr WHERE rr.to = :user) OR a.user.userId IN (SELECT r.to.userId FROM Relationship r WHERE r.from = :user)"),
        //+ " ORDER BY a.postedTime DESC"),
//    @NamedQuery(name="Activity.getFeed",query="SELECT a FROM IsocialActivity a WHERE a.user = :user OR a.user.userId IN (SELECT rr.from.userId FROM Relationship rr WHERE rr.to = :user) OR a.user.userId IN (SELECT r.to.userId FROM Relationship r WHERE r.from = :user) "
//        + " OR  a.igroup.id IN (SELECT gr.igroup.id From GroupRelationship gr where gr.user = :user)  ORDER BY a.postedTime DESC")
    @NamedQuery(name="Activity.getFeed",query="SELECT a FROM IsocialActivity a WHERE  a.igroup.id IN (SELECT gr.igroup.id From GroupRelationship gr where gr.user = :user) ")
})
@XmlRootElement
public class IsocialActivity implements Activity, Serializable {
    private String appId;
    private String body;
    private String bodyId;
    private String externalId;
//    private String id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;
    @OneToMany(mappedBy = "activity", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<IsocialMediaItem> imediaItems;
    private Long postedTime = new Date().getTime();
    private Float priority;
    private String streamFaviconUrl;
//    private String streamSourceUrl;
//    private String streamTitle;
//    private String streamUrl;
    @ElementCollection
//    @CollectionTable(name="activity_templates")
//    @MapKeyColumn(name="key")
//    @Column(name="param")
    private Map<String, String> templateParams;
    private String title;
    private String titleId;
    private String url;
//    private String userId;
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name="activity_comments")
    private List<IsocialActivity> comments;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
//     private ActivityStream activityStream;
    private boolean comment = false;
    @Enumerated(EnumType.STRING)
    private IsocialActivity.Type type = IsocialActivity.Type.USER;
    private boolean hidden = false;
    private List<String> likes;
    private List<String> dislikes;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group igroup;

    public IsocialActivity() {
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBodyId() {
        return this.bodyId;
    }

    public void setBodyId(String bodyId) {
        this.bodyId = bodyId;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<IsocialActivity> getComments() {
        return comments;
    }

    public void setComments(List<IsocialActivity> comments) {
        this.comments = comments;
    }

    public Group getIgroup() {
        return igroup;
    }

    public void setIgroup(Group igroup) {
        this.igroup = igroup;
    }

    
    public String getId() {
        throw new UnsupportedOperationException("Not supported in isocial 1.1");
    }

    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported in isocial 1.1");
    }

    public Date getUpdated() {
        if (this.updated == null) {
            return null;
        }
        return new Date(this.updated.getTime());
    }

    public void setUpdated(Date updated) {
        if (updated == null) {
            this.updated = null;
        } else {
            this.updated = new Date(updated.getTime());
        }
    }

    public List<MediaItem> getMediaItems() {
        throw new UnsupportedOperationException("not included in isocial 1.1");
    }

    public void setMediaItems(List<MediaItem> mediaItems) {
        throw new UnsupportedOperationException("not included in isocial 1.1");
    }

    public Long getPostedTime() {
        return this.postedTime;
    }

    public void setPostedTime(Long postedTime) {
        this.postedTime = postedTime;
    }

    public Float getPriority() {
        return this.priority;
    }

    public void setPriority(Float priority) {
        this.priority = priority;
    }

    public String getStreamFaviconUrl() {
        return this.streamFaviconUrl;
    }

    public void setStreamFaviconUrl(String streamFaviconUrl) {
        this.streamFaviconUrl = streamFaviconUrl;
    }

    public String getStreamSourceUrl() {
        throw new UnsupportedOperationException("not supported in isocial 1.1");
    }

    public void setStreamSourceUrl(String streamSourceUrl) {
        throw new UnsupportedOperationException("not supported in isocial 1.1");
    }

    public String getStreamTitle() {
        throw new UnsupportedOperationException("not supported in isocial 1.1");
    }

    public void setStreamTitle(String streamTitle) {
        throw new UnsupportedOperationException("not supported in isocial 1.1");
    }

    public String getStreamUrl() {
        throw new UnsupportedOperationException("not supported in isocial 1.1");
    }

    public void setStreamUrl(String streamUrl) {
        throw new UnsupportedOperationException("not supported in isocial 1.1");
    }

    public Map<String, String> getTemplateParams() {
        return this.templateParams;
    }

    public void setTemplateParams(Map<String, String> templateParams) {
        this.templateParams = templateParams;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleId() {
        return this.titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        throw new UnsupportedOperationException("not supported in isocial 1.1");
    }

    public void setUserId(String userId) {
        throw new UnsupportedOperationException("not supported in isocial 1.1");
    }

    public List<IsocialMediaItem> getImediaItems() {
        return imediaItems;
    }

    public void setImediaItems(List<IsocialMediaItem> imediaItems) {
        this.imediaItems = imediaItems;
    }
    

    public enum Type {
        USER,TIA,SERVICE,GROUP,RELATIONSHIP
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
