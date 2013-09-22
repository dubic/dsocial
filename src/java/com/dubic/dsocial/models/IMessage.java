/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.models;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.apache.shindig.social.opensocial.model.Url;

/**
 *
 * @author dubic
 */
@Entity
@Table(name="tbl_dsocial_message")
public class IMessage implements org.apache.shindig.social.opensocial.model.Message, Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long uid;
    @Lob
    private String body;
    @Enumerated(EnumType.STRING)
    private IMessage.Status status = Status.NEW;
    private String inReplyTo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timeSent = new Date();
    private String title;
    @Enumerated(EnumType.STRING)
    private IMessage.Type type;
    private List<String> recipients = new ArrayList<String>();
  private List<String> replies = new ArrayList<String>();
  private String senderId;
    @Temporal(javax.persistence.TemporalType.DATE)
  private Date updated = new Date();

    public IMessage() {
    }
    
    public IMessage(String initBody, String initTitle, IMessage.Type initType)
  {
    this.body = initBody;
    this.title = initTitle;
    this.type = initType;
  }


    public String getAppUrl() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAppUrl(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String b) {
        this.body = b;
    }

    public String getBodyId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBodyId(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<String> getCollectionIds() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCollectionIds(List<String> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setId(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getInReplyTo() {
    return this.inReplyTo;
  }

  public void setInReplyTo(String parentId) {
    this.inReplyTo = parentId;
  }

    public List<String> getRecipients() {
    return this.recipients;
  }

  public void setRecipients(List<String> recipients) {
    this.recipients = recipients;
  }

  public List<String> getReplies() {
    return this.replies;
  }

  public void setReplies(List<String> replies) {
    this.replies = replies;
  }

  public String getSenderId() {
    return this.senderId;
  }

  public void setSenderId(String senderId) {
    this.senderId = senderId;
  }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getTimeSent() {
    return this.timeSent;
  }

  public void setTimeSent(Date timeSent) {
    this.timeSent = timeSent;
  }

    public String getTitle() {
    return this.title;
  }

  public void setTitle(String newTitle) {
    this.title = newTitle;
  }

    public String getTitleId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTitleId(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IMessage.Type getType() {
    return this.type;
  }

  public void setType(IMessage.Type newType) {
    this.type = newType;
  }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<Url> getUrls() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setUrls(List<Url> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String sanitizeHTML(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    
  
}
