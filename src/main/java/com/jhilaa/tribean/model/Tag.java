package com.jhilaa.tribean.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;
    private String tagName;
    private String color;

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "tags")
    private Set<Resource> resources = new HashSet<>();

    public Tag() {
    }

    public Tag(String tagName, String color) {
        this.tagName = tagName;
        this.color = color;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int id) {
        this.tagId = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }


}


