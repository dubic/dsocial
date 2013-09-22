package com.dubic.dsocial.models;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import org.primefaces.model.StreamedContent;
//import org.directwebremoting.annotations.DataTransferObject;

/**
 *
 * @author dubic
 */
@Entity(name = "IUser")
@Table(name="tbl_dsocial_user")
@NamedQueries({
        @NamedQuery(name="User.findAll",query="select u from IUser u"),
        @NamedQuery(name="User.find.id",query="SELECT u FROM IUser u WHERE u.userId = :userid")
    })
//@DataTransferObject
@XmlRootElement
public class User implements Serializable {
    
    @Id
    @Column(nullable=false,updatable=false)
    private String userId;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Gender gender;
    private String Url;
    @OneToOne(fetch= FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Avatar avatar;
    private String firstname;
    private String lastname;
    private String email;
    private transient StreamedContent image;
    
//    @ManyToMany(mappedBy = "managers")
//    private List<Service> services;
//    @ManyToOne
//    @JoinColumn(name = "org_id")
//    private Organization organization;
//    @ManyToMany
//    @JoinTable(name = "user_org_conn",joinColumns =@JoinColumn(name = "user_id"),
//    inverseJoinColumns =@JoinColumn(name = "org_id"))
//    private List<Organization> guestOrganizations;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updated = new Date();
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created = new Date();
//  private List<Url> urls;
//  private boolean isOwner = false;
//  private boolean isViewer = false;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    
    public static enum Gender{
        MALE,FEMALE;
    }

    public User() {
    }

    public User(String userid,Gender gender, String firstname, String lastname, String email) {
        this.userId = userid;
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
    

    public String getFullName() {
        if (this.firstname != null) {
            if(this.lastname != null){
               return this.firstname + " "+this.lastname;
            }
            return this.firstname;
        }
        return this.lastname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public StreamedContent getImage() {
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    
//    public Organization getOrganization() {
//        return organization;
//    }

//    public void setOrganization(Organization organization) {
//        this.organization = organization;
//    }

//    public List<Organization> getGuestOrganizations() {
//        return guestOrganizations;
//    }
//
//    public void setGuestOrganizations(List<Organization> guestOrganizations) {
//        this.guestOrganizations = guestOrganizations;
//    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

//    public List<Service> getServices() {
//        return services;
//    }
//
//    public void setServices(List<Service> services) {
//        this.services = services;
//    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if ((this.userId == null) ? (other.userId != null) : !this.userId.equals(other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.userId != null ? this.userId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
}
