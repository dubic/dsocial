/*
 * To change this template; choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofties.jcrest.isocial.dto;

/**
 *
 * @author user
 */
public class IService {

    private String name;
    private String avatarUrl;
    private String desc;
    private String code;
    private String owner;
//    private String[] managers;
//    private String[] members;

    public IService() {
    }

    public IService(String name, String code, String desc,String owner) {
        this.name = name;
        this.desc = desc;
        this.code = code;
        this.owner = owner;
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
}
