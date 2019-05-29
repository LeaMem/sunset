package com.lea.oauth.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class HttpServletDisableSessionRequestWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public HttpServletDisableSessionRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public HttpSession getSession() {
        throw new UnsupportedOperationException("HttpSession is not allowed");
    }

    /**
     * Disable Http Session
     */
    @Override
    public HttpSession getSession(boolean create) {
        throw new UnsupportedOperationException("HttpSession is not allowed");
    }
}
