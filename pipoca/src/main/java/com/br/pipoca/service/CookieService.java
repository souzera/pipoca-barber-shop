package com.br.pipoca.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CookieService {

    public static void setCookie(HttpServletResponse response, String chave, String valor, int tempo){
        Cookie cookie = new Cookie(chave, valor);
        cookie.setMaxAge(tempo);
        response.addCookie(cookie);
    }

    public static String getCookieName(HttpServletRequest request, String chave){
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> chave.equals(cookie.getName()))
                        .findAny())
                .map(e -> e.getValue())
                .orElse(null);
    }

    private static Cookie getCookie(HttpServletRequest request, String chave) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(chave))
                    return cookie;
            }
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest request, String chave){
        Cookie c = getCookie(request, chave);
        return c.getValue();
    }
}
