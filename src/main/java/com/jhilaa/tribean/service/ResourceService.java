package com.jhilaa.tribean.service;

import com.jhilaa.tribean.dto.Mapper;
import com.jhilaa.tribean.dto.requestDto.ResourceRequestDto;
import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    TagRepository tagRepository;

    public List<Resource> findAll () {
        List<Resource> resources = new ArrayList<Resource>();
        resourceRepository.findAll().forEach(resources::add);
        return resources;
    }

    public ResponseEntity<Object> createResource(ResourceRequestDto resourceRequestDto) {
        Resource resource = new Resource();
        resource = Mapper.resourceRequestDtoToResource(resourceRequestDto);
        if (!resourceRepository.findResourceByTitle(resource.getTitle()).isEmpty()) {
            return ResponseEntity.badRequest().body("The resource is already Present, Failed to Create new resource");
        } else {
            Resource savedResource = resourceRepository.save(resource);
            if (resourceRepository.findById(savedResource.getId()).isPresent())
                return ResponseEntity.ok("Resource created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating resource as Specified");
        }
    }

    /**
     * update a existing resource
     */
    @Transactional
    public ResponseEntity<Object> updateResource(Long id, Resource resource) {
        if (resourceRepository.findById(id).isPresent()) {
            Resource newResource = resourceRepository.findById(id).get();
            newResource.setTitle(resource.getTitle());
            newResource.setDescription(resource.getDescription());
            Resource savedResource = resourceRepository.save(newResource);
            //---
            if (resourceRepository.findById(savedResource.getId()).isPresent()) {
                return ResponseEntity.accepted().body("Resource updated successfully");
            } else return ResponseEntity.unprocessableEntity().body("Failed updating the resource specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the resource specified");
    }



    // Delete a resource
    public ResponseEntity<Object> deleteResource(Long id) {
        if (resourceRepository.findById(id).isPresent()) {
            if (resourceRepository.findById(id).get().getTags().size() == 0) {
                resourceRepository.deleteById(id);
                if (resourceRepository.findById(id).isPresent()) {
                    return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
                } else return ResponseEntity.ok().body("Successfully deleted specified record");
            } else
                return ResponseEntity.unprocessableEntity().body("Failed to delete,  Please delete the tags associated with this resource");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }
}
