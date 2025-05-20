package com.a2a2lab.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.member.MemberDao;
import com.a2a2lab.module.member.MemberDto;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MemberDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        MemberDto member = dao.findMemberByEmail(username);

        if (member == null){
            throw  new UsernameNotFoundException("아이디 없음");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(member.getEmail(), member.getPassword(), authorities);
    }
}
