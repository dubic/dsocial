/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.dto;

import com.dubic.dsocial.models.Relationship;

/**
 *
 * @author DUBIC
 */
public class IRelationship {
    private  String fromFullName;
    private  String fromImgUrl;
    private  Long relationshipId;
   
    public IRelationship(Relationship r, String fromImg){
        this.fromFullName = r.getFrom().getFirstname()+" "+r.getFrom().getLastname();
        this.fromImgUrl = fromImg;
        this.relationshipId = r.getId();
    }

    public String getFromFullName() {
        return fromFullName;
    }

    public void setFromFullName(String fromFullName) {
        this.fromFullName = fromFullName;
    }

    public String getFromImgUrl() {
        return fromImgUrl;
    }

    public void setFromImgUrl(String fromImgUrl) {
        this.fromImgUrl = fromImgUrl;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }
    
    
}
