package com.jhilaa.tribean.dto.responseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResourceResponseWithTagIdsListDto extends ResourceResponseDto {
    List<Long> tagIds;
    public ResourceResponseWithTagIdsListDto() {
        super();
        this.tagIds = new ArrayList<>();
    }
}
