package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import com.jhilaa.tribean.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    TagRepository tagRepository;
    //@Autowired
    //UserRepository userRepository;

    @GetMapping(value = "/test/1")
    public ResponseEntity testUnit () {

        Resource resource = new Resource();
        resource.setTitle("title");
        resource.setDescription("description");

        Tag tag = new Tag();
        tag.setName("name");
        tag.setColor("color");

        Tag tag2 = new Tag();
        tag2.setName("name2");
        tag2.setColor("color2");


        return new ResponseEntity("OK", HttpStatus.OK);
    }
}