/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.models;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dubic
 */
@Entity(name="IGroup")
@Table(name="tbl_dsocial_group")
@XmlRootElement
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToOne
    private Avatar avatar;
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="creator_user_id")
    private User creator;
    @Enumerated(EnumType.STRING)
    private Group.Type type = Group.Type.GROUP;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created = new Date();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated = new Date();
    
    public Group(){
    }

    public Group(String name, User creator) {
        this.name = name;
        this.creator = creator;
    }

    public Group(String name, String description, User creator,Group.Type type) {
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Group.Type getType() {
        return type;
    }

    public void setType(Group.Type type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
    
    
    public enum Type{
        TIA,SERVICE,GROUP
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Group other = (Group) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
