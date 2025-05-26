package com.a2a2lab.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.member.MemberDao;
import com.a2a2lab.module.member.MemberDto;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	@Autowired
    MemberDao dao;
	
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        // 예: 구글 로그인 시 사용자 이메일
        String email = oauth2User.getAttribute("email");

        MemberDto member = dao.findMemberByEmail(email);

        if (member == null){
            throw  new UsernameNotFoundException("아이디 없음");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new CustomUserDetails(
                member.getEmail(),
                member.getPassword(),
                member.getName(),
                member.getMemberId(),
                authorities,
                oauth2User.getAttributes()
        );
        
        // 회원가입 되어 있으면 기존 사용자 리턴, 없으면 새로 등록
        // ex) DB에서 Member 찾아보고 없으면 insert

//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
//                oauth2User.getAttributes(),
//                "email"); // usernameKey (주로 email)
    }
}