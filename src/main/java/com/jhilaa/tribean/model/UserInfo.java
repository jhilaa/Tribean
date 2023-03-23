package com.jhilaa.tribean.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInfoId;
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
    //
   //@OneToMany(mappedBy = "resource")
    //private Set<Review> review = new HashSet<>();


    public UserInfo() {
    }

    public UserInfo(String lastname, String firstname, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
