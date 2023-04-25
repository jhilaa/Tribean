package com.jhilaa.tribean.dto.responseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class TagResponseWithResourceResponsesListDto extends TagResponseDto{
    List<ResourceResponseDto> resourceResponseDtoList;

    public TagResponseWithResourceResponsesListDto() {
        super();
        this.resourceResponseDtoList=new ArrayList<>();
    }



}
