package com.br.pipoca.service.authenticator;

import com.br.pipoca.service.CookieService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("opa");
        if (CookieService.getCookieName(request, "login") != null){
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
