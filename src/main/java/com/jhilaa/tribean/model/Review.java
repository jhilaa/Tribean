package com.jhilaa.tribean.model;

import com.jhilaa.tribean.model.param.Rating;
import jakarta.persistence.*;

@Entity
public class Review {
    @EmbeddedId
    private ReviewId id = new ReviewId() ;

    @ManyToOne
    @MapsId("resource_id")
    private Resource resource;

    @ManyToOne
    @MapsId("id_user_info")
    private UserInfo user_info;

    private Rating rating;
    private String comment;

    public Review() {
    }

    public Review(ReviewId id, Resource resource, UserInfo user_info, Rating rating, String comment) {
        this.id = id;
        this.resource = resource;
        this.user_info = user_info;
        this.rating = rating;
        this.comment = comment;
    }

    public ReviewId getId() {
        return id;
    }

    public void setId(ReviewId id) {
        this.id = id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}


