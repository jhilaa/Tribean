package com.jhilaa.tribean.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jhilaa.tribean.service.ResourceService;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="resource")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "title")
    String title;
    @Column(name = "description")
    String description;
    //
    @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
      }
      //, mappedBy = "resources")
    )
    @JsonIgnore
    Set<Tag> tags = new HashSet<>();

    //
    /*
    @OneToMany(
      //mappedBy = "resource",
      cascade = CascadeType.ALL,
      orphanRemoval = true
    )
    List<Review> reviews = new ArrayList<>();
     */
}


