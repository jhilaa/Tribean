package com.jhilaa.tribean.repository.configuration;

import com.jhilaa.tribean.model.UserInfo;
import com.jhilaa.tribean.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // récupérer de la base le user correspondant au login
        //UserInfo user = userInfoRepository.findOneByEmail(login);
        UserInfo user = userInfoRepository.findOneByEmail(login);

        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}