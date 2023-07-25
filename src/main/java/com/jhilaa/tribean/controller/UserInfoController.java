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
public class UserInfoController {
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

    @GetMapping("/logout")
    public ResponseEntity logout() {
        return userInfoService.logout();
    }

    //TODO à déplacer dans UserInfoService
    @GetMapping(value = "/userconnectedinfo")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity getUserConnectedInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserInfo userInfo = userInfoRepository.findOneByEmail(((UserDetails) principal).getUsername());
            return new ResponseEntity(mapper.userInfoToUserInfoResponseDto(userInfo), HttpStatus.OK);
        }
        return new ResponseEntity("User is not connected", HttpStatus.FORBIDDEN);
    }

    //TODO à déplacer dans UserInfoService
    public Long getUserConnectedId(Principal principal) {
        if (!(principal instanceof UsernamePasswordAuthenticationToken)) {
            throw new RuntimeException(("User not found"));
        }
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        UserInfo oneByEmail = userInfoRepository.findOneByEmail(token.getName());
        return oneByEmail.getUserInfoId();
    }
}
