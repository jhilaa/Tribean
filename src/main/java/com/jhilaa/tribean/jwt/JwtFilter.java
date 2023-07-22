package com.jhilaa.tribean.jwt;

import com.jhilaa.tribean.repository.configuration.MyUserDetailService;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.jhilaa.tribean.configuration.Constants.BEARER_AUTHORIZATION_COOKIE;

//import static com.jhilaa.tribean.configuration.Constants.BEARER_AUTHORIZATION_HEADER;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    MyUserDetailService service;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String jwt = resolveToken(request);
        if (StringUtils.hasText(jwt) && !isUrlPermitted(request)) {
            Authentication authentication = jwtUtils.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private boolean isUrlPermitted(HttpServletRequest request) {
        String url = request.getRequestURI();
        System.out.println("url -------------------------");
        System.out.println(url);
        if (url.equals("/authenticate") || url.equals("/user")) {
            return true;
        }
        return false;
    }

    private String resolveToken(HttpServletRequest request) {
        /* avec storage et Cie
        String bearerToken = request.getHeader(BEARER_AUTHORIZATION_HEADER);
        //if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
        if (StringUtils.hasText(bearerToken) ) {
            return bearerToken;
         */

        /* avec cookie */
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(BEARER_AUTHORIZATION_COOKIE)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}