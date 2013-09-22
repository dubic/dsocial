/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.models;

import com.google.gson.Gson;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.shindig.social.opensocial.model.Address;
import org.apache.shindig.social.opensocial.model.MediaItem;

/**
 *
 * @author dubic
 */
@Entity
@Table(name="tbl_isocial_mediaitem")
@XmlRootElement
public class IsocialMediaItem implements MediaItem, Serializable{
//  private String albumId;
  private String created;   //date created in datatype long converted to string
  private String description;
  private String duration;
  private String fileSize;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mediaItemId;
//  private String id;
  
    @ManyToOne
    @JoinColumn(name="activity_id")
  private IsocialActivity activity;
//  private String language;
  private String lastUpdated; //date lastUpdated in datatype long converted to string
//  private Address location;
  private String mimeType;
//  private String numComments;
  private String numViews;
//  private String numVotes;
//  private String rating;
//  private String startTime;
//  private String taggedPeople;
  private String tags;
//  private String thumbnailUrl;
  private String title;
  private MediaItem.Type type;
  private String url;
  
    public IsocialMediaItem(){
    }

    public Long getMediaItemId() {
        return mediaItemId;
    }

    public void setMediaItemId(Long mediaItemId) {
        this.mediaItemId = mediaItemId;
    }

    
    public String getMimeType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMimeType(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Type getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setType(Type type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setUrl(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getThumbnailUrl() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setThumbnailUrl(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAlbumId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAlbumId(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCreated() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCreated(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDescription(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getDuration() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDuration(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getFileSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setFileSize(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setId(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getLanguage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLanguage(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getLastUpdated() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLastUpdated(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Address getLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLocation(Address adrs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNumComments() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNumComments(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNumViews() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNumViews(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNumVotes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNumVotes(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRating() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setRating(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getStartTime() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setStartTime(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getTaggedPeople() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTaggedPeople(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getTags() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTags(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getTitle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTitle(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    ////////////////

    public IsocialActivity getActivity() {
        return activity;
    }

    public void setActivity(IsocialActivity activity) {
        this.activity = activity;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
