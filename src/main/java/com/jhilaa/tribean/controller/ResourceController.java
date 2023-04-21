package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import com.jhilaa.tribean.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//TODO décommenter pour remettre les sécus //@SecurityRequirement(name = "bearerAuth")
public class ResourceController {

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceService resourceService;
    @Autowired
    TagRepository tagRepository;

    //-- CREATE
    @PostMapping("/resources/create")
    public ResponseEntity<Object> createResource(@RequestBody Resource resource) {
        return resourceService.createResource(resource);
    }

    //-- SELECT
    @GetMapping("/resources/all")
    public List<Resource> getResources() {
        return resourceService.findAll();
    }

    @GetMapping("/resources/{resourceId}")
    public Resource getResource(@PathVariable Long resourceId) {
        if (resourceRepository.findById(resourceId).isPresent())
            return resourceRepository.findById(resourceId).get();
        else return null;
    }


    //-- UPDATE ----------------------
    @PutMapping("/resource/{resourceId}/update")
    public ResponseEntity<Object> updateResource(@PathVariable Long resourceId, @RequestBody Resource resource) {
        return resourceService.updateResource(resourceId, resource);
    }

    @PutMapping("/resources/{resourceId}/addtag/{tagId}")
    public ResponseEntity<Tag> addTagToResource(@PathVariable Long resourceId, Long tagId) {
        Resource resource = resourceRepository.findById(resourceId)
          .orElseThrow(() -> new ResourceNotFoundException("Not found Resource with id = " + resourceId));
        Tag tag = tagRepository.findById(tagId)
          .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));

        resource.addTag(tag);
        resourceRepository.save(resource);
        tag.addResource(resource);
        tagRepository.save(tag);

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @DeleteMapping("/resources/{resourceId}/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTagFromResource(@PathVariable Long resourceId, Long tagId) {
        Resource resource = resourceRepository.findById(resourceId)
          .orElseThrow(() -> new ResourceNotFoundException("Not found Resource with id = " + resourceId));
        Tag tag = tagRepository.findById(tagId)
          .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));

        resource.removeTag(tagId);
        resourceRepository.save(resource);

        tag.removeResource(resourceId);
        tagRepository.save(tag);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //-- DELETE
    @DeleteMapping("/resources/{resourceId}/delete")
    public ResponseEntity<Object> deleteResource(@PathVariable Long resourceId) {
        return resourceService.deleteResource(resourceId);
    }

}