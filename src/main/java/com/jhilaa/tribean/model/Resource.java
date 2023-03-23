package com.jhilaa.tribean.model;

import com.jhilaa.tribean.model.param.Visibility;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resourceId;
    @NotNull
    private String title;
    private String description;
    private String imgUrl;
    private Visibility visibility;
    private LocalDate creationDate;
    private LocalDate modificationDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="resources_tags_association", joinColumns = @JoinColumn(name="resourceId"),
            inverseJoinColumns = @JoinColumn(name="tagId"))
    private Set<Tag> tags = new HashSet<Tag>();


    public Resource() {
    }

    public Resource(String title, String description, String imgUrl, Visibility visibility, LocalDate creationDate, LocalDate modificationDate) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.visibility = visibility;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public int getResourceId() {
        return resourceId;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getResources().add(this);
    }

    public void removeTag(long tagId) {
        Tag tag = this.tags.stream().filter(t -> t.getTagId() == tagId).findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getResources().remove(this);
        }
    }
}
