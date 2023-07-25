package com.jhilaa.tribean.service;

import com.jhilaa.tribean.model.Credentials;
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
            UserInfo recorderUserInfo = userInfoRepository.save(user);

            UserInfo recorderUserInfoTest = userInfoRepository.findOneByEmail(recorderUserInfo.getEmail());
            if (recorderUserInfoTest==null ) {
                return new ResponseEntity("Données utilisateur non enregistrées", HttpStatus.BAD_REQUEST);
            }
            else {
                ResponseEntity<Object> response = login(new Credentials(newUserInfo.getEmail(), newUserInfo.getPassword()));
                return response;
            }
        }

    }

    public ResponseEntity<Object> login(Credentials credentials) {
        Authentication authentication = jwtController.logUser(credentials);
        String jwt = jwtUtils.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Set-Cookie", BEARER_AUTHORIZATION_COOKIE + "=" + jwt + "; Max-Age=604800; Path=/; Secure; HttpOnly");
        return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
    }


    public ResponseEntity<Object> logout() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Set-Cookie", BEARER_AUTHORIZATION_COOKIE + "; Max-Age=0; Path=/;");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}