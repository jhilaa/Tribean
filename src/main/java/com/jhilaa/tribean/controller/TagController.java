package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import com.jhilaa.tribean.service.TagService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TagController {
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    TagService tagService;



    //-- CREATE
    @PostMapping("/tags/add")
    public ResponseEntity<Object> createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    //-- SELECT
    @GetMapping("/tags/all")
    public List<Tag> getTags() {
        return tagService.findAll();
        /*
        List<Tag> tags = new ArrayList<Tag>();
        tagRepository.findAll().forEach(tags::add);
        return tags;
         */
    }

    @GetMapping("/tags/{tagId}")
    public Tag getTag(@PathVariable Long tagId) {
        if (tagRepository.findById(tagId).isPresent())
            return tagRepository.findById(tagId).get();
        else return null;
    }

    //-- UPDATE
    @PutMapping("/tags/{tagId}/edit")
    public ResponseEntity<Object> updateTag(@PathVariable Long tagId, @RequestBody Tag tag) {
        return tagService.updateTag(tagId,tag);
    }

    //-- DELETE
    @DeleteMapping("tags/delete/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
        //TODO delete du tag dans les ressources
    }



}


