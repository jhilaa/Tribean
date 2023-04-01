package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;


@RestController
@SecurityRequirement(name = "bearerAuth")
    public class ResourceController {

        @Autowired
        ResourceRepository resourceRepository;
        //TagRepository tagRepository;

        //get de toutes les ressources
        @GetMapping("/resources")
        public ResponseEntity<List<Resource>> getAllResources(@RequestParam(required = false) String string) {
            List<Resource> resources = new ArrayList<Resource>();
            if (string == null)
                resourceRepository.findAll().forEach(resources::add);
            else
                resourceRepository.findByTitleContaining(string).forEach(resources::add);

            if (resources.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resources, HttpStatus.OK);
        }

        @GetMapping("/resources/{id}")
        public ResponseEntity<Resource> getResourceById(@PathVariable("id") long id) {
            Resource resource = resourceRepository.findById(id).get();
              //.orElseThrow(() -> new ChangeSetPersister.NotFoundException("Not found Resource with id = " + id));

            return new ResponseEntity<>(resource, HttpStatus.OK);
        }

        @PostMapping("/resources")
        public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
            Resource _resource = resourceRepository.save(new Resource(resource.getTitle(), resource.getDescription()));
            return new ResponseEntity<>(_resource, HttpStatus.CREATED);
        }

        @PutMapping("/resources/{id}")
        public ResponseEntity<Resource> updateResource(@PathVariable("id") long id, @RequestBody Resource resource) {
            Resource _resource = resourceRepository.findById(id).get();
              //.orElseThrow(() -> new NotFoundException("Not found Resource with id = " + id)));

            _resource.setTitle(resource.getTitle());
            _resource.setDescription(resource.getDescription());
            return new ResponseEntity<>(resourceRepository.save(_resource), HttpStatus.OK);
        }

        @DeleteMapping("/resources/{id}")
        public ResponseEntity<HttpStatus> deleteResource(@PathVariable("id") long id) {
            resourceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/resources")
        public ResponseEntity<HttpStatus> deleteAllResources() {
            resourceRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }