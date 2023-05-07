package com.jhilaa.tribean.dto;

import com.jhilaa.tribean.dto.requestDto.ResourceRequestDto;
import com.jhilaa.tribean.dto.responseDto.ResourceResponseDto;
import com.jhilaa.tribean.dto.responseDto.ResourceResponseWithTagResponsesListDto;
import com.jhilaa.tribean.dto.responseDto.TagResponseDto;
import com.jhilaa.tribean.dto.responseDto.TagResponseWithResourceResponsesListDto;
import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class Mapper {
    private static TagRepository tagRepository;
    private static ResourceRepository resourceRepository;
    @Autowired
    public Mapper(TagRepository tagRepository,
                  ResourceRepository resourceRepository) {
        Mapper.tagRepository = tagRepository;
        Mapper.resourceRepository = resourceRepository;
    }

    public static ResourceResponseDto resourceToResourceResponseDto (Resource resource) {
        ResourceResponseDto resourceResponseDto = new ResourceResponseDto();
        //
        resourceResponseDto.setId(resource.getId());
        resourceResponseDto.setTitle(resource.getTitle());
        resourceResponseDto.setDescription(resource.getDescription());
        return resourceResponseDto;
    }

    public static ResourceResponseWithTagResponsesListDto resourceToResourceResponseWithTagResponsesListDto (Resource resource) {
        ResourceResponseWithTagResponsesListDto resourceResponseWithTagResponsesListDto
          = new ResourceResponseWithTagResponsesListDto();

        resourceResponseWithTagResponsesListDto.setId(resource.getId());
        resourceResponseWithTagResponsesListDto.setTitle(resource.getTitle());
        resourceResponseWithTagResponsesListDto.setDescription(resource.getDescription());
        Set<Tag> tags = resource.getTags();
        TagResponseDto tagResponseDto = new TagResponseDto();
        for (Tag tag: tags) {
            tagResponseDto.setId(tag.getId());
            tagResponseDto.setName(tag.getName());
            tagResponseDto.setColor(tag.getColor());
            resourceResponseWithTagResponsesListDto.getTagResponseDtoList().add(tagResponseDto);
        }
    return resourceResponseWithTagResponsesListDto;
    }

    public static Resource resourceRequestDtoToResource(ResourceRequestDto resourceRequestDto) {

        Resource resource = new Resource();
        resource.setTitle(resourceRequestDto.getTitle());
        resource.setDescription(resourceRequestDto.getDescription());
        // tags
        HashSet<Tag> tags = new HashSet<>();
        for (Long tagId : resourceRequestDto.getTagIds()) {
            tags.add(tagRepository.findById(tagId).get());
        }
        resource.setTags(tags);
        //TODO continuer la création d'une ressource
        return resource;
    }

    public static TagResponseDto tagToTagResponseDto (Tag tag) {
        TagResponseDto tagResponseDto = new TagResponseDto();
            tagResponseDto.setId(tag.getId());
            tagResponseDto.setName(tag.getName());
            tagResponseDto.setColor(tag.getColor());
        return tagResponseDto;
    }

    public static TagResponseWithResourceResponsesListDto tagToTagResponseWithResourceResponseListDto (Tag tag) {
        TagResponseWithResourceResponsesListDto tagResponseWithResourceResponsesListDto = new TagResponseWithResourceResponsesListDto();
        tagResponseWithResourceResponsesListDto.setId(tag.getId());
        tagResponseWithResourceResponsesListDto.setName(tag.getName());
        tagResponseWithResourceResponsesListDto.setColor(tag.getColor());

        Set<Resource> resources = tag.getResources();
        ResourceResponseDto resourceResponseDto = new ResourceResponseDto();
        for (Resource resource: resources) {
            resourceResponseDto.setId(resource.getId());
            resourceResponseDto.setTitle(resource.getTitle());
            resourceResponseDto.setDescription(resource.getDescription());
            tagResponseWithResourceResponsesListDto.getResourceResponseDtoList().add(resourceResponseDto);
        }

        return tagResponseWithResourceResponsesListDto;
    }

}
