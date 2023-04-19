package com.jhilaa.tribean.service;

import com.jhilaa.tribean.jwt.JwtFilter;
import com.jhilaa.tribean.model.UserInfo;
import com.jhilaa.tribean.repository.UserInfoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.jhilaa.tribean.jwt.JwtController;
import com.jhilaa.tribean.jwt.JwtFilter;
import com.jhilaa.tribean.jwt.JwtUtils;
import org.springframework.http.HttpHeaders;

@Service
public class UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    JwtController jwtController;

    public ResponseEntity<Object> createUser(UserInfo newUserInfo) {
        userInfoRepository.save(newUserInfo);
        //
        //@TODO ko à analyser
        Authentication authentication = jwtController.logUser(newUserInfo.getEmail(), newUserInfo.getPassword());
        String jwt = jwtUtils.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        //
        return new ResponseEntity<>(newUserInfo, httpHeaders, HttpStatus.OK);
    }
}
