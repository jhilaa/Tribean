package com.jhilaa.tribean.service;

import com.jhilaa.tribean.dto.Mapper;
import com.jhilaa.tribean.dto.requestDto.ResourceRequestDto;
import com.jhilaa.tribean.dto.responseDto.ResourceResponseDto;
import com.jhilaa.tribean.dto.responseDto.ResourceResponseWithTagResponsesListDto;
import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    Mapper mapper;

    public List<ResourceResponseWithTagResponsesListDto> findAll () {
        List<ResourceResponseWithTagResponsesListDto> resourceResponseWithTagResponsesListDto = new ArrayList<ResourceResponseWithTagResponsesListDto>();
        for (Resource resource : resourceRepository.findAll())
        {resourceResponseWithTagResponsesListDto.add(mapper.resourceToResourceResponseWithTagResponsesListDto(resource));}
        return resourceResponseWithTagResponsesListDto;
    }

    public ResourceResponseWithTagResponsesListDto findById ( Long id) {
        Resource resource = resourceRepository.findById(id).get();
        return mapper.resourceToResourceResponseWithTagResponsesListDto(resource);
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

    @Transactional
    public ResponseEntity<Object> updateResource(ResourceRequestDto resourceRequestDto) {
        System.out.println (resourceRequestDto);
        if (resourceRepository.findById(resourceRequestDto.getId()).isPresent()) {
            Resource resource = resourceRepository.findById(resourceRequestDto.getId()).get();
            resource.setTitle(resourceRequestDto.getTitle());
            resource.setDescription(resourceRequestDto.getDescription());
            resource.setTags(new HashSet<>());
            for (Long tagId:resourceRequestDto.getTagIds()
                 ) {resource.getTags().add(tagRepository.findById(tagId).get());
            }

            Resource savedResource = resourceRepository.save(resource);
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
