package com.voting.listener;

import com.voting.pojo.Users;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

/**
 * @author: ll
 * @description: session监听器
 * @create: 2019-10-23 18:04
 **/
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            ServletContext application = session.getServletContext();
            @SuppressWarnings("unchecked")
            Map<String, Object> loginMap = (Map<String, Object>) application.getAttribute("loginMap");
            loginMap.remove(user.getUserId().toString());
            application.setAttribute("loginMap", loginMap);
            session.removeAttribute("user");
        }
    }
}
