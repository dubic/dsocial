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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dubic
 */
@Entity
@Table(name="tbl_dsocial_org")
//@DataTransferObject
@NamedQueries({
    @NamedQuery(name="Organization.find.all",query="SELECT o FROM Organization o ORDER BY o.created"),
    @NamedQuery(name="Organization.find.portal",query="SELECT o FROM Organization o WHERE o.portalName = :portalName ORDER BY o.created")
})
@XmlRootElement
public class Organization implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long  id;
    private String name;
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="creator_user_id")
    private User creator;
    @Column(unique=false,nullable=false)
    private String portalName;
    private String website;
    private String description;
    @OneToOne
    private Avatar avatar ;
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name="group_id")
    private Group igroup;
    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.PUBLIC;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created = new Date();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated = new Date();
    
    public Organization(){
    }

    public Organization(String name, User creator, String portalName) {
        this.name = name;
        this.creator = creator;
        this.portalName = portalName;
    }

    public Organization(String name, User creator, String portalName, Group group) {
        this.name = name;
        this.creator = creator;
        this.portalName = portalName;
        this.igroup = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

    public String getPortalName() {
        return portalName;
    }

    public void setPortalName(String portalName) {
        this.portalName = portalName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
    
    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
