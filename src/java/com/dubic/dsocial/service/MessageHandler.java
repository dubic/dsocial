/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.service;

import com.dubic.dsocial.dao.exceptions.NonexistentEntityException;
import com.dubic.dsocial.models.IMessage;
import com.dubic.dsocial.models.User;
import com.dubic.dsocial.util.LinkUtils;
import com.esofties.jcrest.isocial.dto.IActivity;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dubic
 */
@Named
@Transactional
public class MessageHandler {

    private Logger log = Logger.getLogger(getClass());
    //    30 * 24 * 60 * 60 * 1000;
    @Inject
    Database db;
    @Inject
    LinkUtils lnk;

    public MessageHandler() {
    }

    public void saveMessage(IMessage msg) {

        IMessage msgs = db.edit(msg);
        log.info("message saved for " + msgs.getUid());
    }

    public void loadWelcomeActivity(String userName,List<IActivity> acts) throws NonexistentEntityException, Exception {
        log.info("loading welcome activity");
        User u = db.find(User.class, userName);
        ResourceBundle bundle = ResourceBundle.getBundle("resources.conf");
       Calendar cal1 = Calendar.getInstance();
        long timeInMillis1 = cal1.getTimeInMillis();
        cal1.add(Integer.parseInt(bundle.getString("welcome.activity.active.time.interval")), -1*Integer.parseInt(bundle.getString("welcome.activity.active.time.length")));
//        cal1.add(Calendar.DAY_OF_MONTH, -30);
        long timeInMillis2 = cal1.getTimeInMillis();
    }
}
