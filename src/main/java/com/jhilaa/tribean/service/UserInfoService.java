package com.jhilaa.tribean.service;

import com.jhilaa.tribean.model.UserInfo;
import com.jhilaa.tribean.repository.UserInfoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import com.jhilaa.tribean.jwt.JwtController;
import com.jhilaa.tribean.jwt.JwtUtils;
import org.springframework.http.HttpHeaders;

import static com.jhilaa.tribean.configuration.Constants.*;

@Service
public class UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    JwtController jwtController;

    public ResponseEntity<Object> createUser(UserInfo newUserInfo) {
        UserInfo existingUser = userInfoRepository.findOneByEmail(newUserInfo.getEmail());
        System.out.println("**************");
    if (existingUser != null) {
            return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
        } else {
            UserInfo user = new UserInfo();
            user.setEmail(newUserInfo.getEmail());
            user.setPassword(new BCryptPasswordEncoder().encode(newUserInfo.getPassword()));
            user.setLastname(StringUtils.capitalize(newUserInfo.getLastname()));
            user.setFirstname(StringUtils.capitalize(newUserInfo.getFirstname()));

            userInfoRepository.save(user);
            //
            Authentication authentication = jwtController.logUser(newUserInfo.getEmail(), newUserInfo.getPassword());
            String jwt = jwtUtils.generateToken(authentication);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(BEARER_AUTHORIZATION_HEADER, jwt);
            httpHeaders.add("Set-Cookie", BEARER_AUTHORIZATION_COOKIE +"="+jwt+"; Max-Age=604800; Path=/; Secure; HttpOnly");
            return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
        }
    }
}