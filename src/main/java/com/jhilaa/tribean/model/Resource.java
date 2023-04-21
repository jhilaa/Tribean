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
import java.util.stream.Collectors;

@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode
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
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
      CascadeType.MERGE})
    @JoinTable(name = "resource_tags",
      joinColumns = { @JoinColumn(name = "resource_id") },
      inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<>();

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getResources().add(this);
    }

    public void removeTag(long tagId) {
        Tag tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getResources().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Ressource {" +
          "id=" + id +
          ", title='" + title + '\'' +
          ", tags='" + tags.stream().map(Tag::getName).collect(Collectors.toList()) + '\'' +
          '}';
    }

}


