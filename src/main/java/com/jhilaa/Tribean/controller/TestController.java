package com.jhilaa.tribean.controller;

import com.jhilaa.tribean.model.Review;
import com.jhilaa.tribean.model.ReviewId;
import com.jhilaa.tribean.model.param.Rating;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.ReviewRepository;
import com.jhilaa.tribean.repository.TagRepository;
import com.jhilaa.tribean.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/test/")
    public ResponseEntity test() {
        Integer userId = 1;
        Integer resourceId = 1;
        Review myReview = new Review();
        myReview.setId(new ReviewId(resourceId,userId));
        myReview.setComment("commentaire");
        myReview.setRating(Rating.ONE);
        myReview.setResource(resourceRepository.findById(resourceId).get());
        myReview.setUser_info(userRepository.findById(userId).get());
        reviewRepository.save(myReview);
        return new ResponseEntity(myReview, HttpStatus.CREATED);
    }
}
