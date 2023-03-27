package com.jhilaa.tribean.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userInfoId;
    @NotBlank
    @Size(min=2, max=25, message="Le prénom doit faire entre 2 et 25 caractères")
    String lastname;
    @NotBlank
    @Size(min=2, max=25, message="Le nom doit faire entre 2 et 25 caractères")
    String firstname;
    @NotBlank
    String email;
    @NotBlank
    String password;
    //@OneToMany(mappedBy = "resource")
    //private Set<Review> review = new HashSet<>();


}
