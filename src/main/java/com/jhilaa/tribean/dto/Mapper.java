package com.jhilaa.tribean.dto;

import com.jhilaa.tribean.dto.responseDto.ResourceResponseDto;
import com.jhilaa.tribean.dto.responseDto.ResourceResponseWithTagIdsListDto;
import com.jhilaa.tribean.dto.responseDto.ResourceResponseWithTagResponsesListDto;
import com.jhilaa.tribean.dto.responseDto.TagResponseDto;
import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;

import java.util.Set;

public class Mapper {

    public static ResourceResponseDto resourceToResourceResponseDto (Resource resource) {
        ResourceResponseDto resourceResponseDto = new ResourceResponseDto();
        //
        resourceResponseDto.setId(resource.getId());
        resourceResponseDto.setTitle(resource.getTitle());
        resourceResponseDto.setDescription(resource.getDescription());
        return resourceResponseDto;
    }

    public static ResourceResponseWithTagIdsListDto resourceToResourceResponseWithTagIdsListDto (Resource resource) {
        ResourceResponseWithTagIdsListDto resourceResponseWithTagIdsListDto = new ResourceResponseWithTagIdsListDto();
        //
        resourceResponseWithTagIdsListDto.setId(resource.getId());
        resourceResponseWithTagIdsListDto.setTitle(resource.getTitle());
        resourceResponseWithTagIdsListDto.setDescription(resource.getDescription());
        Set<Tag> tags = resource.getTags();
        for (Tag tag: tags) {
            resourceResponseWithTagIdsListDto.getTagIds().add(tag.getId());
        }
        return resourceResponseWithTagIdsListDto;
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
}
