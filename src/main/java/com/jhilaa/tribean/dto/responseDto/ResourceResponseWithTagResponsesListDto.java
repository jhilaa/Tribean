package com.jhilaa.tribean.dto.responseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.jhilaa.tribean.dto.responseDto.TagResponseDto;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ResourceResponseWithTagResponsesListDto extends ResourceResponseDto{
    List<TagResponseDto> tagResponseDtoList;

    public ResourceResponseWithTagResponsesListDto() {
        super();
        this.tagResponseDtoList=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Ressource {" +
          "id=" + this.getId() +
          ", title='" + this.getTitle() + '\'' +
          ", tags='" + this.getTagResponseDtoList().stream().map(TagResponseDto::getName).collect(Collectors.toList()) + '\'' +
          '}';
    }
}
