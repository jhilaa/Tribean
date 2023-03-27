package com.jhilaa.tribean.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Objects;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"tagId", "resourceId"})
@Embeddable
public class ResourcesTagsAssociationPK implements Serializable {
        Integer tagId;
        Integer resourceId;
}



