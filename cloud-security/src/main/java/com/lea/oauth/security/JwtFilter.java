package com.lea.oauth.security;

import com.lea.oauth.config.HttpServletDisableSessionRequestWrapper;
import com.lea.oauth.jwt.JwtProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");

        Authentication authentication = null;
        try {
            authentication = jwtProvider.decodeToken(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //自定义无状态 request
        HttpServletDisableSessionRequestWrapper wrapper = new HttpServletDisableSessionRequestWrapper(request);


        filterChain.doFilter(wrapper, response);

    }


}
