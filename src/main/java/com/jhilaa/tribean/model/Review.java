package com.jhilaa.tribean.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode (exclude = "id")
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    //
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    Resource resource;
    //
    int rating;
    String comment;

    //TODO user/rédacteur
}
