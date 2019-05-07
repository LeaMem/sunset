package com.lea.oauth.security;


import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;

public class CustomInvocationSecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {


    private final FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    public CustomInvocationSecurityMetaDataSource(FilterInvocationSecurityMetadataSource expressionBasedFilterInvocationSecurityMetadataSource) {
        this.filterInvocationSecurityMetadataSource = expressionBasedFilterInvocationSecurityMetadataSource;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl = filterInvocation.getRequestUrl();

        if (requestUrl.equals("/h")) {

            return SecurityConfig.createList("resource:read");


        }

        //返回代码定义的
        return filterInvocationSecurityMetadataSource.getAttributes(object);

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}
