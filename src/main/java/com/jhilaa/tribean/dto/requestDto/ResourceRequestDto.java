package com.jhilaa.tribean.dto.requestDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ResourceRequestDto {
    String title;
    String description;
    Long tagId;
}
