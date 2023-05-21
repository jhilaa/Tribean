package com.jhilaa.tribean.dto.responseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserInfoResponseDto {
    String email;
    String firstname;
    String lastname;
}
