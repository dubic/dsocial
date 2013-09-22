/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esofties.jcrest.isocial.dto;
///List<IGroup> myconnections
///List<IGroup> rec_connections

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ITia {
    private String name;
    private String description;
    private String label;
    private String logoUrl;
    private List<IService> connectedServices = new ArrayList<IService>(); 
    
    public ITia(){
    }

    public ITia(String name,String logo) {
        this.name = name;
        this.logoUrl = logo;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    
    public List<IService> getConnectedServices() {
        return connectedServices;
    }

    public void setConnectedServices(List<IService> connectedServices) {
        this.connectedServices = connectedServices;
    }
    
    public void addConnectedService(IService connectedService) {
        this.connectedServices.add(connectedService);
    }
    
}
