package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.jwt.JwtController;
import com.jhilaa.tribean.jwt.JwtUtils;
import com.jhilaa.tribean.model.UserInfo;
import com.jhilaa.tribean.repository.UserInfoRepository;
import com.jhilaa.tribean.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    UserInfoService userInfoService;
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

    @PostMapping("/user")
    public ResponseEntity addNewUser(@RequestBody UserInfo userInfo) {

        return userInfoService.createUser(userInfo);
    }

    //TODO à déplacer dans UserInfoService
    @GetMapping(value = "/isConnected")
    public ResponseEntity getUSerConnected() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return new ResponseEntity(((UserDetails) principal).getUsername(), HttpStatus.OK);
        }
        return new ResponseEntity("User is not connected", HttpStatus.FORBIDDEN);
    }

}
