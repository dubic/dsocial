/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.dsocial.web;



import com.dubic.dsocial.util.LinkUtils;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author dubic
 */
public class AvatarServlet extends HttpServlet {

   
    private LinkUtils lnk;
    
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("avatar servlet called");
        response.setContentType("image/jpeg");
        String pid = request.getParameter("pid");
        byte[] avatar = null;
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        lnk = ctx.getBean(LinkUtils.class);
        if (pid != null) {
            avatar = lnk.getAvatarImage(Long.parseLong(pid));
        }
        if (avatar == null) {
            avatar = "".getBytes();
        }
        response.getOutputStream().write(avatar);

    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
