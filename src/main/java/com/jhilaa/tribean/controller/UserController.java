package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.jwt.JwtController;
import com.jhilaa.tribean.jwt.JwtFilter;
import com.jhilaa.tribean.jwt.JwtUtils;
import com.jhilaa.tribean.model.UserInfo;
import com.jhilaa.tribean.repository.UserInfoRepository;
import com.jhilaa.tribean.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
@RestController
public class UserController {
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    JwtController jwtController;

    @GetMapping("/user/all")
    public ResponseEntity getAllUsers() {
        return new ResponseEntity(userInfoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getUser(@PathVariable("userId") String userId) {
        return new ResponseEntity(userInfoRepository.findById(Long.valueOf(userId)), HttpStatus.OK);
    }

    @PostMapping("/user/add")
    public ResponseEntity addUser(@Valid @RequestBody UserInfo userInfo) {
        UserInfo existingUser = userInfoRepository.findOneByEmail(userInfo.getEmail());
        if(existingUser != null) {
            return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
        }
        UserInfo user = saveUser(userInfo);
        Authentication authentication = jwtController.logUser(userInfo.getEmail(), userInfo.getPassword());
        String jwt = jwtUtils.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/isConnected")
    public ResponseEntity getUSerConnected() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return new ResponseEntity(((UserDetails) principal).getUsername(), HttpStatus.OK);
        }
        return new ResponseEntity("User is not connected", HttpStatus.FORBIDDEN);
    }

    private UserInfo saveUser(UserInfo userInfo) {
        UserInfo user = new UserInfo();
        user.setEmail(userInfo.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
        user.setLastname(StringUtils.capitalize(userInfo.getLastname()));
        user.setFirstname(StringUtils.capitalize(userInfo.getFirstname()));
        userInfoRepository.save(user);
        return user;
    }

}
