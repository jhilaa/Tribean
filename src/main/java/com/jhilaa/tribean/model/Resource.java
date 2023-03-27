package com.jhilaa.tribean.model;

import com.jhilaa.tribean.model.enumeration.Visibility;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

import java.time.LocalDate;
import java.util.Set;

@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer resourceId;
    String title;
    String description;
    String imgUrl;
    Visibility visibility;
    LocalDate creationDate;
    LocalDate modificationDate;
    //
    @OneToMany(mappedBy ="tagId" )
    Set<Tag> tags;

}
