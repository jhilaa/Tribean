package com.jhilaa.tribean.jwt;

import com.jhilaa.tribean.repository.configuration.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.jhilaa.tribean.configuration.Constants.BEARER_AUTHORIZATION_COOKIE;

@RestController
public class JwtController {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    MyUserDetailService service;
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
        Authentication authentication = logUser(jwtRequest.getEmail(), jwtRequest.getPassword());
        String jwt = jwtUtils.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("BEARER_AUTHORIZATION_HEADER", jwt);
        httpHeaders.add("Set-Cookie", BEARER_AUTHORIZATION_COOKIE +"="+jwt+"; Max-Age=604800; Path=/; Secure; HttpOnly");
        Object principal = authentication.getPrincipal();
        return new ResponseEntity<>(new JwtResponse(((User) principal).getUsername()), httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/cookie")
    public ResponseEntity deleteCookie(String cookie) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Set-Cookie", BEARER_AUTHORIZATION_COOKIE +"= Path=/; Max-Age=0");
        return new ResponseEntity<>(
          "Delete cookies", httpHeaders, HttpStatus.OK);
    }

    public Authentication logUser(String mail, String password) {
        //exception
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

}