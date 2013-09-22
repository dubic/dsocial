/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.models;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="tbl_dsocial_relationship")
@NamedQueries({
    @NamedQuery(name="Relationship.exists",query="SELECT r FROM Relationship r WHERE (r.from.userId = :fromId OR r.to.userId = :fromId) AND (r.from.userId = :toId OR r.to.userId = :toId)"),//and r.to.userId = (:fromId OR :toId)
    @NamedQuery(name="Relationship.find.level",query="SELECT r FROM Relationship r WHERE r.level = :level and (r.from = :user OR r.to = :user)"),
    @NamedQuery(name="Relationship.find.level.to",query="SELECT r FROM Relationship r WHERE r.level = :level and r.to = :user"),
    @NamedQuery(name="Relationship.find.level.from.to",query="SELECT r FROM Relationship r WHERE r.level = :level AND (r.from = :user OR r.to = :user)")
})
@XmlRootElement
public class Relationship implements Serializable {
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created = new Date();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated = new Date();
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="from_user_id")
    private User from;
    @ManyToOne
    @JoinColumn(name="to_user_id")
    private User to;
    private String howKnow;
    @Column(unique=true)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name="rel_level")
    private LEVEL level;

    public Relationship(User from, User to, String howKnow) {
        this.from = from;
        this.to = to;
        this.howKnow = howKnow;
        this.level = LEVEL.PENDING;
        this.name = from.getUserId()+"_"+to.getUserId();
    }

    public Relationship(User from, User to, String howKnow, LEVEL level) {
        this.from = from;
        this.to = to;
        this.howKnow = howKnow;
        this.level = level;
        this.name = from.getUserId()+"_"+to.getUserId();
    }
    
    

    public Relationship(Long id) {
        this.id = id;
    }
    
    public enum LEVEL{
        PENDING,REJECTED,ACCEPTED,IGNORED;
    }
    public Relationship(){
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getHowKnow() {
        return howKnow;
    }

    public void setHowKnow(String howKnow) {
        this.howKnow = howKnow;
    }

    public LEVEL getLevel() {
        return level;
    }

    public void setLevel(LEVEL level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Relationship other = (Relationship) obj;
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
