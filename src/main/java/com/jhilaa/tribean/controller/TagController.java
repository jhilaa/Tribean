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
        public ResponseEntity<List<Tag>> getAllTagsByResourceId(@PathVariable(value = "resourceId") Integer resourceId) {
            if (!resourceRepository.existsById(resourceId)) {
                //throw new ResourceNotFoundException("Not found Resource with id = " + resourceId);
            }

            List<Tag> tags = tagRepository.findTagsByResourcesResourceId(resourceId);
            return new ResponseEntity<>(tags, HttpStatus.OK);
        }

        @GetMapping("/tags/{id}")
        public ResponseEntity<Tag> getTagsById(@PathVariable(value = "id") Integer id) {
            Tag tag = tagRepository.findById(id).get();
                    //TODO gestion des exceptions
                    //.orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));

            return new ResponseEntity<>(tag, HttpStatus.OK);
        }

        @GetMapping("/tags/{tagId}/resources")
        public ResponseEntity<Set<Resource>> getAllResourcesByTagId(@PathVariable(value = "tagId") Integer tagId) {
            if (!tagRepository.existsById(tagId)) {
                //TODO gestion des exceptions
                //throw new ResourceNotFoundException("Not found Tag  with id = " + tagId);
            }
            Set<Resource> resources = resourceRepository.findResourcesByTagsTagId(tagId);
            return new ResponseEntity<>(resources, HttpStatus.OK);
        }

        @PostMapping("/resources/{resourceId}/tag")
        public ResponseEntity<Tag> addTag(@PathVariable(value = "resourceId") Integer resourceId, @RequestBody Tag newTag) {
            Resource resource = resourceRepository.findById(resourceId).get();
            Integer tagId = newTag.getTagId();
                // tag is existed
                if (tagId != 0L) {
                    Tag _tag = tagRepository.findById(tagId).get();
                            //TODO gestion des exceptions
                            // .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
                    resource.addTag(_tag);
                    resourceRepository.save(resource);
                    return new ResponseEntity<> (HttpStatus.OK);
                }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping("/tags/{id}")
        public ResponseEntity<Tag> updateTag(@PathVariable("id") Integer id, @RequestBody Tag tagRequest) {
            Tag tag = tagRepository.findById(id).get();
            /*TODO gestion des exceptions */
            /*        .orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));*/
            tag.setTagName(tagRequest.getTagName());

            return new ResponseEntity<>(tagRepository.save(tag), HttpStatus.OK);
        }

        @DeleteMapping("/resources/{resourceId}/tags/{tagId}")
        public ResponseEntity<HttpStatus> deleteTagFromResource(@PathVariable(value = "resourceId") Integer resourceId, @PathVariable(value = "tagId") Long tagId) {
            Resource resource = resourceRepository.findById(resourceId).get();
                    //TODO gestion des exceptions
                    // .orElseThrow(() -> new ResourceNotFoundException("Not found Resource with id = " + resourceId));

            resource.removeTag(tagId);
            resourceRepository.save(resource);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/tags/{id}")
        public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") Integer id) {
            tagRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

