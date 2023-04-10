package com.jhilaa.tribean.service;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    TagRepository tagRepository;

    /** Create a new Resource */
    public ResponseEntity<Object> createResource(Resource newResource) {
        Resource resource = new Resource();
        if (!resourceRepository.findResourceByTitle(newResource.getTitle()).isEmpty()) {
            return ResponseEntity.badRequest().body("The resource is already Present, Failed to Create new resource");
        } else {
            resource.setTitle(newResource.getTitle());
            resource.setDescription(newResource.getDescription());

            Resource savedResource = resourceRepository.save(resource);
            if (resourceRepository.findById(savedResource.getId()).isPresent())
                return ResponseEntity.ok("Resource created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating resource as Specified");
        }
    }

    /** Create a new resource  */
    @Transactional
    public ResponseEntity<Object> updateResource(Resource resource)  {
        Resource newResource = new Resource();
        newResource.setTitle(resource.getTitle());
        newResource.setDescription(resource.getDescription());
        Set<Resource> resourceList = new HashSet<>();
        resourceList.add(newResource);
        for (Tag tag : resource.getTags()) {
            if(tagRepository.findTagByName(tag.getName()) == null) {
                Tag newTag = tag;
                newTag.setResources(resourceList);
                Tag savedTag = tagRepository.save(newTag);
                if(! tagRepository.findById(savedTag.getId()).isPresent())
                    return ResponseEntity.unprocessableEntity().body("Resource Creation Failed");
            }
            else  return   ResponseEntity.unprocessableEntity().body("Resource with this title is already Present");
        }
        return ResponseEntity.ok("Successfully created Resource");
    }

    public ResponseEntity<Object> deleteResource(Long id) {
        if(resourceRepository.findById(id).isPresent()){
            if(resourceRepository.findById(id).get().getTags().size() == 0) {
                resourceRepository.deleteById(id);
                if (resourceRepository.findById(id).isPresent()) {
                    return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
                } else return ResponseEntity.ok().body("Successfully deleted specified record");
            } else return ResponseEntity.unprocessableEntity().body("Failed to delete,  Please delete the tags associated with this resource");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }


    /** Update a Resource */
    public ResponseEntity<Object> updateResource(Long id, Resource resource) {
        if(resourceRepository.findById(id).isPresent()){
            Resource newResource = resourceRepository.findById(id).get();
            newResource.setTitle(resource.getTitle());
            newResource.setDescription(resource.getDescription());
            Resource savedResource = resourceRepository.save(newResource);
            if(resourceRepository.findById(savedResource.getId()).isPresent())
                return ResponseEntity.accepted().body("Resource saved successfully");
            else return ResponseEntity.badRequest().body("Failed to update Resource");

        } else return ResponseEntity.unprocessableEntity().body("Specified Resource not found");
    }
}
