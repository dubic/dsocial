/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.web;



import com.dubic.dsocial.util.LinkUtils;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.HttpRequestHandler;

/**
 *
 * @author dubic
 */
public class AvatarServlet implements HttpRequestHandler {

    @Inject
    private LinkUtils lnk;
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        String pid = request.getParameter("pid");
        byte[] avatar = null;
        if (pid != null) {
            avatar = lnk.getAvatarImage(Long.parseLong(pid));
        }
        if (avatar == null) {
            avatar = "".getBytes();
        }
        response.getOutputStream().write(avatar);

    }
}
