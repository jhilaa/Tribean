package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Review;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@SecurityRequirement(name = "bearerAuth")
    public class ResourceController {

        @Autowired
        ResourceRepository resourceRepository;
        TagRepository tagRepository;

        @GetMapping("/resources")
        public ResponseEntity<List<Resource>> getAllResources(@RequestParam(required = false) String tagId) {
            List<Resource> resources = new ArrayList<Resource>();

            if (tagId == null)
                resourceRepository.findAll().forEach(resources::add);
            else {
                Optional<Resource> resource = resourceRepository.findById(Integer.valueOf(tagId));
                if (! resource.isEmpty()) {
                    resources.add(resource.get());
                }
            }

            if (resources.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resources, HttpStatus.OK);
        }

        /*
        @GetMapping("/resources/{id}")
        public ResponseEntity<Resource> getResourceById(@PathVariable("id") String id) {
            Resource resource = resourceRepository.findById(Integer.valueOf(id)).get();
                    //.orElseThrow(() -> new ResourceNotFoundException("Not found Resource with id = " + id));

            return new ResponseEntity<>(resource, HttpStatus.OK);
        }

         */

    @GetMapping("/resources/{id}/reviews")
    public ResponseEntity<Set<Review>> getReviewsByresourceId(@PathVariable("id") String id) {
        Set<Review> reviews = resourceRepository.findReviewsByResourceId(Integer.valueOf(id));
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/resources")
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource _resource = resourceRepository.save(new Resource(resource.getTitle(), resource.getDescription(), resource.getImgUrl(), resource.getVisibility(), resource.getCreationDate(), resource.getModificationDate()));
        return new ResponseEntity<>(_resource, HttpStatus.CREATED);
    }

    @PutMapping("/resources/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable("id") Integer id, @RequestBody Resource resource) {
        Resource _resource = resourceRepository.findById(id).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Not found Resource with id = " + id));

        _resource.setTitle(resource.getTitle());
        _resource.setDescription(resource.getDescription());
        _resource.setImgUrl(resource.getImgUrl());

        return new ResponseEntity<>(resourceRepository.save(_resource), HttpStatus.OK);
    }

    @DeleteMapping("/resources/{id}")
    public ResponseEntity<HttpStatus> deleteResource(@PathVariable("id") Integer id) {
        resourceRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/resources")
    public ResponseEntity<HttpStatus> deleteAllResources() {
        resourceRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}