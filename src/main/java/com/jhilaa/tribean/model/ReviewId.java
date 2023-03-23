package com.jhilaa.tribean.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public /*static*/ class ReviewId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer resourceId;
    private Integer userId;

    public ReviewId() {
    }

    public ReviewId(Integer resourceId, Integer userId) {
        this.resourceId = resourceId;
        this.userId = userId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((resourceId == null) ? 0 : resourceId.hashCode());
        result = prime * result
                + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReviewId other = (ReviewId) obj;
        return Objects.equals(getResourceId(), other.getResourceId()) && Objects.equals(getUserId(), other.getUserId());
    }
}
