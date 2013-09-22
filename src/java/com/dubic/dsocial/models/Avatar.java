/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author dubic
 */
@Entity
@Table(name="tbl_isocial_avatar")
public class Avatar implements Serializable {
    private String name;
    private String description;
    
    private byte[] image;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created = new Date();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated = new Date();
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "avatar")
    private User user;
    
    @Enumerated(EnumType.STRING)
    private Avatar.OwnerType type;
    @OneToOne(mappedBy = "avatar")
    private Organization organization;
    @OneToOne(mappedBy = "avatar")
    private Group igroup;

    public Avatar(String name, User user, OwnerType type) {
        this.name = name;
        this.user = user;
        this.type = type;
    }
    
    public Avatar(){
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Avatar.OwnerType getType() {
        return type;
    }

    public void setType(Avatar.OwnerType type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Group getIgroup() {
        return igroup;
    }

    public void setIgroup(Group igroup) {
        this.igroup = igroup;
    }
    
    
    public enum OwnerType {
        USER,TIA,SERVICE
    }
}
