package com.jhilaa.tribean.dto.requestDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Data
@Component
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ResourceRequestDto {
    Long id;
    String title;
    String description;
    HashSet<Long> tagIds;
}
