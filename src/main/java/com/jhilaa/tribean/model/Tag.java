package com.jhilaa.tribean.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "color")
    String color;
    //
    @ManyToMany(fetch = FetchType.LAZY,
      cascade = { CascadeType.PERSIST,
                CascadeType.MERGE},
      mappedBy = "tags")
    Set<Resource> resources = new HashSet<>();

    public void addResource(Resource resource) {
        resources.add(resource);
        resource.getTags().add(this);
    }

    public void removeResource(long resourceId) {
        Resource resource = this.resources.stream().filter(t -> t.getId() == resourceId).findFirst().orElse(null);
        if (resource != null) {
            this.resources.remove(resource);
            resource.getTags().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Tag{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", resource='" + resources.stream().map(Resource::getTitle).collect(Collectors.toList()) + '\'' +
          '}';
    }
}


