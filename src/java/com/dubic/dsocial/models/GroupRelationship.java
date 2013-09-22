/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.models;

import com.dubic.dsocial.models.Relationship.LEVEL;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dubic
 */
@Entity
@Table(name="tbl_isocial_grprel")
@NamedQueries({
    @NamedQuery(name="GroupRelationship.find.all",query="SELECT r FROM GroupRelationship r "),
    @NamedQuery(name="GroupRelationship.find.user.level",query="SELECT r FROM GroupRelationship r WHERE r.user = :user and r.level = :level"),
    @NamedQuery(name="GroupRelationship.find.user.type",query="SELECT r FROM GroupRelationship r WHERE r.user = :user and r.memberType = :type"),
    @NamedQuery(name="GroupRelationship.find.groups",query="SELECT r FROM GroupRelationship r WHERE r.igroup = :group"),
    @NamedQuery(name="GroupRelationship.find.userGroups.level",query="SELECT r.igroup FROM GroupRelationship r WHERE r.user = :user AND r.level = :level ORDER BY r.created"),
    @NamedQuery(name="GroupRelationship.exists",query="SELECT r.igroup FROM GroupRelationship r WHERE r.user = :user AND r.igroup = :group")
})
@XmlRootElement
public class GroupRelationship implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch= FetchType.LAZY)
    private Group igroup;
    @ManyToOne(fetch= FetchType.LAZY)
    private User user;
    @Enumerated(EnumType.STRING)
    private RelationshipType memberType = RelationshipType.MEMBER;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar created = Calendar.getInstance();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar updated = Calendar.getInstance();
    private boolean fromUser;
    
    @Enumerated(EnumType.STRING)
    @Column(name="rel_level")
    private LEVEL level = LEVEL.PENDING;
    
    public GroupRelationship(){
    }

    public GroupRelationship(Group group, User user, boolean fromUser) {
        this.igroup = group;
        this.user = user;
        this.fromUser = fromUser;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getIgroup() {
        return igroup;
    }

    public void setIgroup(Group igroup) {
        this.igroup = igroup;
    }

    public RelationshipType getMemberType() {
        return memberType;
    }

    public void setMemberType(RelationshipType memberType) {
        this.memberType = memberType;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }

    public boolean isFromUser() {
        return fromUser;
    }

    public void setFromUser(boolean fromUser) {
        this.fromUser = fromUser;
    }

    public LEVEL getLevel() {
        return level;
    }

    public void setLevel(LEVEL level) {
        this.level = level;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    public enum RelationshipType{MEMBER,FOUNDER,ADMIN}
}
