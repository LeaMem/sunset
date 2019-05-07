package com.lea.oauth.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Qualifier("customUserDetails")
public class CustomUserDetails implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var authList = getAuthorities();

        if (username.isBlank() || !username.equals("kitty")) {
            throw new UsernameNotFoundException("用户名密码错误");
        }

        return new User(username, "123456", authList);

    }


    private Collection<GrantedAuthority> getAuthorities() {

        var authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("resource:read"));
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return authList;
    }

}
