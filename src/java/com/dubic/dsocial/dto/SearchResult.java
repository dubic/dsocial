/*
 * To change this template; choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.dto;


/**
 *
 * @author dubic
 */
public class SearchResult {
    private String imageUrl;
    private String category;
    private String fullName;
    private String statistics;
    private String sectionType;
//    private String email;
//    private String orgId;

    public SearchResult(String imageUrl,String fullname,String category,String statistics,String sec) {
        this.fullName = fullname;
        this.imageUrl = imageUrl;
        this.category = category;
        this.statistics = statistics;
        this.sectionType = sec;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    

    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "SearchResult{" + "imageUrl=" + imageUrl + ", category=" + category + ", fullName=" + fullName + ", statistics=" + statistics + ", sectionType=" + sectionType + '}';
    }

    
    
}
