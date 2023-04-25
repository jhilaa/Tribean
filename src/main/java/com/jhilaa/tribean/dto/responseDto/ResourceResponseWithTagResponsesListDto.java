package com.jhilaa.tribean.dto.responseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ResourceResponseWithTagResponsesListDto extends ResourceResponseDto{
    List<TagResponseDto> tagResponseDtoList;

    public ResourceResponseWithTagResponsesListDto() {
        super();
        this.tagResponseDtoList=new ArrayList<>();
    }



}
