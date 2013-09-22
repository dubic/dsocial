/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.util;

import com.dubic.dsocial.models.IMessage;
import org.apache.shindig.social.opensocial.model.Message;

/**
 *
 * @author dubic
 */

public class MessageUtils {
    public static final String SENDER_JCREST = "JCrest";
    
//   @RemoteMethod
    public static IMessage createSimpleMessage(String sender,String recipient,String body){
        IMessage m = new IMessage();
        m.setType(Message.Type.NOTIFICATION);
        m.setStatus(Message.Status.NEW);
        m.setSenderId(sender);
        m.getRecipients().add(recipient);
        m.setBody(body);
        return m;
    }
    
    public static IMessage createWelcomeMessage(String recipient,String body){
        IMessage m = new IMessage();
        m.setType(Message.Type.PRIVATE_MESSAGE);
        m.setStatus(Message.Status.NEW);
        m.setSenderId(SENDER_JCREST);
        m.getRecipients().add(recipient);
        m.setBody(body);
        return m;
    }
}
