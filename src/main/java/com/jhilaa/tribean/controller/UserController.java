package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.dto.Mapper;
import com.jhilaa.tribean.jwt.JwtController;
import com.jhilaa.tribean.jwt.JwtUtils;
import com.jhilaa.tribean.model.UserInfo;
import com.jhilaa.tribean.repository.UserInfoRepository;
import com.jhilaa.tribean.service.UserInfoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    @Autowired
    Mapper mapper;

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

    @GetMapping(value = "/userconnectedinfo")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity getUserConnectedInfo() {
        UserInfo userInfo = userInfoService.getUserConnectedInfo();
        if (userInfo == null) {
            return new ResponseEntity(mapper.userInfoToUserInfoResponseDto(userInfo), HttpStatus.OK);
        }
        return new ResponseEntity("User is not connected", HttpStatus.FORBIDDEN);
    }

}
