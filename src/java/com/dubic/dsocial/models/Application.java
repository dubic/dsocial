/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.models;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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

/**
 *
 * @author dubic
 */
@Entity
@Table(name="tbl_dsocial_app")
//@DataTransferObject
public class Application implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String displayName;
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="creator_user_id")
    private User creator;
    private String code;// cannot be unique
    private String description;
    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.PRIVATE;
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="group_id")
    private Group igroup;
    
    @OneToOne
    private Avatar avatar ;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created = new Date();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated = new Date();
    
    public Application(){
    }

    public Application(String name, User creator, String code) {
        this.name = name;
        this.creator = creator;
        this.code = code;
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Group getIgroup() {
        return igroup;
    }

    public void setIgroup(Group igroup) {
        this.igroup = igroup;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
