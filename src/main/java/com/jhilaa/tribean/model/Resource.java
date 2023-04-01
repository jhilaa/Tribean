package com.jhilaa.tribean.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jhilaa.tribean.model.enumeration.Visibility;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

import java.time.LocalDate;
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
    @ManyToMany(
      fetch = FetchType.LAZY,
      cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE})
    @JoinTable(name="tutorials_tags_relation",
      joinColumns = {@JoinColumn(name="resource_id")},
      inverseJoinColumns = {@JoinColumn(name="tag_id")})
    Set<Tag> tags = new HashSet<>();

    public Resource (String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void removeTag(long tagId) {
        Tag tag = this.tags.stream().filter(tg -> tg.getId() == tagId).findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getResources().remove(this);
        }
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getResources().add(this);
    }
}


