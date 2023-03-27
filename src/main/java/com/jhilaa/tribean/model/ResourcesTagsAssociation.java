package com.jhilaa.tribean.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

// lombok
@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//
@Entity
public class ResourcesTagsAssociation {
    @EmbeddedId
    ResourcesTagsAssociationPK resourcesTagsAssociationId;
    @ManyToOne
    @MapsId("resourceId")
    @JoinColumn(name = "resource_id")
    Resource resource;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    Tag tag;
}



