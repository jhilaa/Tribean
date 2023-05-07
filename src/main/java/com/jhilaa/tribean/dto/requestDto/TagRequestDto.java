package com.jhilaa.tribean.dto.requestDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class TagRequestDto {
    Long id;
    String name;
    String color;
}
