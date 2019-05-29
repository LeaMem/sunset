package com.lea.oauth.security;


import com.lea.api.entity.Resource;
import com.lea.api.mapper.ResourceMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. 从 web expression 中查找 资源需要的权限
 * 2. 从 数据库 中查找资源需要的权限
 * 3. 将两份合并
 */
public class CustomInvocationSecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {

    private final FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    private ResourceMapper resourceMapper;

    public CustomInvocationSecurityMetaDataSource(FilterInvocationSecurityMetadataSource expressionBasedFilterInvocationSecurityMetadataSource,
                                                  ResourceMapper resourceMapper) {
        this.filterInvocationSecurityMetadataSource = expressionBasedFilterInvocationSecurityMetadataSource;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl = filterInvocation.getRequestUrl();

        List<Resource> resourceList = resourceMapper.listAllByUrl(requestUrl);

        List<String> permissionList = resourceList.stream()
                .map(Resource::getRequirePermission)
                .collect(Collectors.toList());

        //返回代码定义的
        Collection<ConfigAttribute> webExpressions = filterInvocationSecurityMetadataSource.getAttributes(object);

        if (!CollectionUtils.isEmpty(permissionList)) {
            List<ConfigAttribute> list = SecurityConfig.createList(permissionList.toArray(new String[0]));
            webExpressions.addAll(list);
        }

        return webExpressions;

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
