package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TagController {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = new ArrayList<Tag>();
        tagRepository.findAll().forEach(tags::add);

        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
       return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/resources/{resourceId}/tags")
    public ResponseEntity<List<Tag>> getAllTagsByResourceId(@PathVariable(value = "resourceId") Long resourceId) {
        if (!resourceRepository.existsById(resourceId)) {
            //throw new ResourceNotFoundException("Not found Resource with id = " + resourceId);
        }

        List<Tag> tags = tagRepository.findTagsByResourcesId(resourceId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTagsById(@PathVariable(value = "id") Long id) {
        Tag tag = tagRepository.findById(id).get();
          ;//.orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    //get de toutes les resources par tag id
    @GetMapping("/tags/{tagId}/resources")
    public ResponseEntity<List<Resource>> getAllResourcesByTagId(@PathVariable(value = "tagId") Long tagId) {
        if (!tagRepository.existsById(tagId)) {
            // throw new ResourceNotFoundException("Not found Tag  with id = " + tagId);
        }
        List<Resource> resources = resourceRepository.findResourcesByTagsId(tagId);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @PostMapping("/resources/{resourceId}/tags")
    public ResponseEntity<Tag> addTag(@PathVariable(value = "resourceId") Long resourceId, @RequestBody Tag tagRequest) {
        Tag tag = resourceRepository.findById(resourceId).map(resource -> {
            long tagId = tagRequest.getId();

            // tag is existed
            if (tagId != 0L) {
                Tag _tag = tagRepository.findById(tagId).get();
                  //.orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
                resource.addTag(_tag);
                resourceRepository.save(resource);
                return _tag;
            }

            // add and create new Tag
            resource.addTag(tagRequest);
            return tagRepository.save(tagRequest);
        }).get(); //.orElseThrow(() -> new ResourceNotFoundException("Not found Resource with id = " + resourceId));

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tagRequest) {
        Tag tag = tagRepository.findById(id).get()
          ; //.orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));

        tag.setName(tagRequest.getName());
        tag.setColor(tagRequest.getColor());

        return new ResponseEntity<>(tagRepository.save(tag), HttpStatus.OK);
    }

    @DeleteMapping("/resources/{resourceId}/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTagFromResource(@PathVariable(value = "resourceId") Long resourceId, @PathVariable(value = "tagId") Long tagId) {
        Resource resource = resourceRepository.findById(resourceId).get()
         ;// .orElseThrow(() -> new ResourceNotFoundException("Not found Resource with id = " + resourceId));

        resource.removeTag(tagId);
        resourceRepository.save(resource);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
        tagRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


