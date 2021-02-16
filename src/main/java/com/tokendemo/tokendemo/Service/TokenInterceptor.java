package com.tokendemo.tokendemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenInterceptor implements AsyncHandlerInterceptor {

    @Autowired
    TokenService tokenService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response) {

        String header = request.getHeader("authorization");

        if(header == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try {

            String token = header.replace("Bearer ", "");
            String user = tokenService.getSubject(token);
            request.setAttribute("user", user);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }

        return true;
    }

}
