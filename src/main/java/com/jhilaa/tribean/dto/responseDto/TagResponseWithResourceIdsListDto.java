package com.jhilaa.tribean.dto.responseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class TagResponseWithResourceIdsListDto extends TagResponseDto {
    List<Long> resourceIds;

    public TagResponseWithResourceIdsListDto() {
        super();
        this.resourceIds = new ArrayList<>();
    }
}
