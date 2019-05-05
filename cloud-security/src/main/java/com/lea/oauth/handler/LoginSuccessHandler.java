package com.lea.oauth.handler;

import org.apache.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求体中client信息为空");
        }

//        try {
//
////            String[] tokens = extractAndDecodeHeader(header);
////            String clientId = tokens[0];
//
//
//        }

    }

//    public String[] extractAndDecodeHeader(String header) throws UnsupportedEncodingException {
//
//        byte[] base64Token = header.substring(6).getBytes("UTF-8");
//        byte[] decoded;
//
//        try {
//            decoded = Base64.decode(base64Token);
//
//        } catch (Exception e) {
//            throw new RuntimeException(
//                    "Failed to decode basic authentication token");
//        }
//        String token = new String(decoded, "UTF-8");
//
//        int delim = token.indexOf(":");
//        if (delim == -1) {
//            throw new RuntimeException("Invalid basic authentication token");
//        }
//        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
//
//    }
}
