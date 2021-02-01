package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("用户名称：" + s);
        // 根据用户名查询用户信息l
        return new User(s, passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return new User(s, passwordEncoder.encode("123456"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
