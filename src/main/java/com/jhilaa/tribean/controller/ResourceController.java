package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.dto.Mapper;
import com.jhilaa.tribean.dto.requestDto.ResourceRequestDto;
import com.jhilaa.tribean.dto.responseDto.ResourceResponseDto;
import com.jhilaa.tribean.dto.responseDto.ResourceResponseWithTagResponsesListDto;
import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import com.jhilaa.tribean.service.ResourceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class ResourceController {

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceService resourceService;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    ResourceRequestDto resourceRequestDto;

    //TODO responseEntity

    //-- CREATE
    @PostMapping("/resources/")
    public ResponseEntity<Object> createResource(@RequestBody ResourceRequestDto resourceRequestDto) {
        return resourceService.createResource(resourceRequestDto);
    }

    //-- SELECT
    @GetMapping("/resources/all")
    public List<ResourceResponseWithTagResponsesListDto> getResources() {

        return resourceService.findAll();
    }

    @GetMapping("/resources/{resourceId}")
    public ResourceResponseWithTagResponsesListDto getResource(@PathVariable Long resourceId) {
        return resourceService.findById(resourceId);
    }


    //-- UPDATE ----------------------
    @PutMapping("/resources")
    public ResponseEntity<Object> updateResource(@RequestBody ResourceRequestDto resourceRequestDto) {
        return resourceService.updateResource(resourceRequestDto);
    }

    //-- DELETE
    @DeleteMapping("/resources/{resourceId}")
    public ResponseEntity<Object> deleteResource(@PathVariable Long resourceId) {
        return resourceService.deleteResource(resourceId);
    }

}