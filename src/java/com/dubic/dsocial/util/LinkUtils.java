/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.util;

import com.dubic.dsocial.models.Avatar;
import com.dubic.dsocial.models.Group;
import com.dubic.dsocial.models.Organization;
import com.dubic.dsocial.models.User;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author dubic
 */
@Named("lnk")
public class LinkUtils {

    public static String DEFAULT_MALE_AVARTAR = "avatars/male.jpg";
    public static final String DEFAULT_FEMALE_AVARTAR = "avatars/female.jpg";
    public static final String DEFAULT_TIA_LOGO = "avatars/org1.jpg";
    public static final String DEFAULT_SERVICE_LOGO = "avatars/service6.jpg";
    public static String JCREST_LOGO = "avatars/logo.jpg";
    private static String avatarServlet = "avatar?pid=";
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LinkUtils.class);
    @PersistenceContext
    private EntityManager em;
    private String DEFAULT_GROUP_LOGO = "avatars/org2.jpg";
    private String domain;

    public LinkUtils() {
//        img = testS();
        
    }
    private StreamedContent img;

    public String getDomain() {
//        ServletRequestAttributes sr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        domain = sr.getRequest().getRequestURL().toString();
        return ResourceContants.SERVER_PATH;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAvatarURL(User u) {
        log.debug("getAvatarURL{}");
        Avatar avatar = u.getAvatar();
        if (avatar == null) {
            if (u.getGender() == User.Gender.MALE) {
                return getDomain() + DEFAULT_MALE_AVARTAR;
//             
            } else {
                return getDomain()  + DEFAULT_FEMALE_AVARTAR;
            }
        } else {
          return getDomain() +avatarServlet+avatar.getId();
        }
    }

    public String getTiaLogoURL(Organization o) {
        Avatar a = o.getAvatar();
        if (a == null) {
            return getDomain() + DEFAULT_TIA_LOGO;
        }
        return getDomain() + avatarServlet + a.getId();

    }

//    public String getServiceLogoURL(Service s) {
//        Avatar a = s.getAvatar();
//        if (a == null) {
//           return  getDomain()+DEFAULT_SERVICE_LOGO;
//        } 
//            return  getDomain()+avatarServlet + a.getId();
//     
//    }
    public String getJCREST_LOGO() {
        return getDomain() + JCREST_LOGO;
    }

    public byte[] getAvatarImage(Long pid) {
        Avatar av = em.find(Avatar.class, pid);
        return av.getImage();
    }

    public String getGroupLogo(Group grp) {
        Avatar a = grp.getAvatar();
        if (a == null) {
            return getDomain() + DEFAULT_GROUP_LOGO;
        }
        return getDomain() + avatarServlet + a.getId();
    }

    public byte[] getTestImage() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/avatars/male.jpg");
        return IOUtils.toByteArray(is);
    }

    public StreamedContent testS() {
        System.out.println("GET AVATAR URL");

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/avatars/male.jpg");
//        
        return new DefaultStreamedContent(is, "image/jpeg");

    }

    public StreamedContent getImg() {
        return img;
    }

    public void setImg(StreamedContent img) {
        this.img = img;
    }
}
