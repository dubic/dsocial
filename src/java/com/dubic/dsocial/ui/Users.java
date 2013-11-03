/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.ui;

import com.dubic.dsocial.dao.exceptions.NonexistentEntityException;
import com.dubic.dsocial.dto.IUser;
import com.dubic.dsocial.models.User;
import com.dubic.dsocial.service.IsocialRelationshipException;
import com.dubic.dsocial.service.RelationshipHandler;
import com.dubic.dsocial.service.UserHandler;
import com.dubic.dsocial.util.LinkUtils;
import com.dubic.dsocial.util.MessageUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
/**
 *
 * @author DUBIC
 */
@Named("users")
@Scope(value = "request")
public class Users {

    private Logger log = Logger.getLogger(getClass());
    private IUser iuser = new IUser();
    private User user = new User();
    private List<IUser> iuserList = new ArrayList<IUser>();
    @Inject
    private UserHandler uh;
    @Inject
    private LinkUtils lnk;
    @Inject
    private RelationshipHandler rh;
    @Inject
    private Session social;

    public Users() {
    }

    public void loadUsers() {
        List<User> userList = uh.findUsers();
        for (User u : userList) {
            IUser iUser = new IUser(u, lnk.getAvatarURL(u));
            iuserList.add(iUser);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IUser getIuser() {
        return iuser;
    }

    public void setIuser(IUser iuser) {
        this.iuser = iuser;
    }

    public List<IUser> getIuserList() {
        return iuserList;
    }

    public void setIuserList(List<IUser> iuserList) {
        this.iuserList = iuserList;
    }

    public void createUser() {
        log.debug(user.toString());

        try {
            User u = uh.createUser(user);
            FacesContext.getCurrentInstance().addMessage("userCreateMsg", new FacesMessage(u.getUserId() + " created"));
        } catch (Exception ex) {

            FacesContext.getCurrentInstance().addMessage("userCreateMsg", new FacesMessage(ex.getMessage()));
            log.error(ex);
            ex.printStackTrace();
        }
    }

    public void details(AjaxBehaviorEvent evt) {
        System.out.println("event - " + evt.getComponent().getParent().getId());
        HtmlCommandButton btn = (HtmlCommandButton) evt.getComponent();
        Map<String, Object> attributes = btn.getAttributes();
        System.out.println("reading test btn attributes size - " + attributes.size());
        for (String key : attributes.keySet()) {
            System.out.println(key + " - " + attributes.get(key));
        }
        Map btnPropMap;
        try {
            btnPropMap = BeanUtils.describe(btn);
            System.out.println("reading btn prop map size - " + btnPropMap.size());
            for (Object key : btnPropMap.keySet()) {
                System.out.println(key + " - " + btnPropMap.get(key));
            }
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }

//    System.out.println("btn called - "+btn.);
    }

    public void requestConnection(AjaxBehaviorEvent evt) {
        UIComponent component = evt.getComponent();
        System.out.println("name - " + component.getClientId());

//        Map<Object, Object> attributes = FacesContext.getCurrentInstance().getAttributes();
//        for (Object key : attributes.keySet().toArray()) {
//            System.out.println(key+" - "+attributes.get(key));
//    }
        try {
            //save img bytes in avatar
            System.out.println("selected user - " + social.getSelectedUser().getUserId());
            rh.requestRelationship(social.getUser().getUserId(), social.getSelectedUser().getUserId(), null);
            MessageUtils.addMessage("connmsgs", FacesMessage.SEVERITY_INFO, "request connection sent..");
//            component.setValueExpression("value", );
        } catch (NonexistentEntityException ex) {
            MessageUtils.addMessage("connmsgs", FacesMessage.SEVERITY_ERROR, "user not found");
        } catch (IsocialRelationshipException ex) {
            MessageUtils.addMessage("connmsgs", FacesMessage.SEVERITY_ERROR, "relationship exists");
        } catch (Exception ex) {
            MessageUtils.addMessage("connmsgs", FacesMessage.SEVERITY_ERROR, "error in sending relationship request");
            ex.printStackTrace();
        }
    }
}
