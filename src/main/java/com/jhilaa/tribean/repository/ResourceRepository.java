package com.jhilaa.tribean.repository;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Integer> {
    //TODO les méthodes qui complètent : findAll, ...
    Set<Resource> findResourcesByTagsTagId(Integer tagId);

    Set<Review> findReviewsByResourceId(Integer valueOf);
}

