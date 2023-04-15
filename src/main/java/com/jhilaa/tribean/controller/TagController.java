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

    @PostMapping("/tag/create")
    public ResponseEntity<Object> createTag(@RequestBody Tag tag) {
        //TODO vérif existence tag
        return tagService.createTag(tag);
    }

    @GetMapping("/tag/details/{id}")
    public Tag getTag(@PathVariable Long id) {
        if(tagRepository.findById(id).isPresent())
            return tagRepository.findById(id).get();
        else return  null;
    }
    @GetMapping("/tag/all")
    public List<Tag> getTags() {
        List<Tag> tags = new ArrayList<Tag>();
        tagRepository.findAll().forEach(tags::add);
        return tags;
    }
    @PutMapping("/tag/update/{id}")
    public ResponseEntity<Object> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        return tagService.updateTag(tag, id);
    }

    @DeleteMapping("tag/delete/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }
}


