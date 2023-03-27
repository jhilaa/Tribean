package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@SecurityRequirement(name = "bearerAuth")
    public class ResourceController {

        @Autowired
        ResourceRepository resourceRepository;
        TagRepository tagRepository;

        //get de toutes les ressources
        @GetMapping("/resources")
        public ResponseEntity<List<Resource>> getAllResources() {
            List<Resource> resources = new ArrayList<Resource>();
            resourceRepository.findAll().forEach(resources::add);
            if (resources.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(resources, HttpStatus.OK);
        }

        //création d'une ressource
        @PostMapping("/resources")
        public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
            Resource _resource = resourceRepository.save(resource);
            return new ResponseEntity<>(_resource, HttpStatus.CREATED);
        }

        //mise à jour d'une resource
        @PutMapping("/resources/{id}")
        public ResponseEntity<Resource> updateResource(@PathVariable("id") Integer id, @RequestBody Resource resource) {
            Resource _resource = resourceRepository.findById(id).get();
                    //.orElseThrow(() -> new ResourceNotFoundException("Not found Resource with id = " + id));

            _resource.setTitle(resource.getTitle());
            _resource.setDescription(resource.getDescription());
            _resource.setImgUrl(resource.getImgUrl());

            return new ResponseEntity<>(resourceRepository.save(_resource), HttpStatus.OK);
        }

        //suppression d'une ressource
        @DeleteMapping("/resources/{id}")
        public ResponseEntity<HttpStatus> deleteResource(@PathVariable("id") Integer id) {
            resourceRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // suppression de toutes les ressource
        @DeleteMapping("/resources")
        public ResponseEntity<HttpStatus> deleteAllResources() {
            resourceRepository.deleteAll();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }