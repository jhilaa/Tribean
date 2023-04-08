package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Review;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.service.ResourceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

@RestController
@SecurityRequirement(name = "bearerAuth")
    public class ResourceController {

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceService resourceService;

    @PostMapping("/resource/create")
    public ResponseEntity<Object> createResource(@RequestBody Resource resource) {
        return  resourceService.addResource(resource);
    }
    @DeleteMapping("/resource/delete/{id}")
    public ResponseEntity<Object> deleteResource(@PathVariable Long id) {
        return resourceService.deleteResource(id);
    }

    @GetMapping("/resource/details/{id}")
    public Resource getResource(@PathVariable Long id) {
        if(resourceRepository.findById(id).isPresent())
            return resourceRepository.findById(id).get();
        else return null;
    }
    @GetMapping("/resource/all")
    public List<Resource> getResources() {
        List<Resource> resources = new ArrayList<Resource>();
        resourceRepository.findAll().forEach(resources::add);
        return resources;
    }
    @PutMapping("/resource/update/{id}")
    public ResponseEntity<Object> updateResource(@PathVariable Long id, @RequestBody Resource resource) {
        return resourceService.updateResource(id, resource);
    }
    }