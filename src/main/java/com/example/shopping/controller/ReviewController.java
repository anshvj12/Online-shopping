package com.example.shopping.controller;

import com.example.shopping.Model.Review;
import com.example.shopping.dao.ReviewRepository;
import com.example.shopping.request.InpReview;
import com.example.shopping.response.RespOrder;
import com.example.shopping.response.RespReview;
import com.example.shopping.service.ReviewService;
import com.example.shopping.service.ReviewServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public List<Review> getReviews() {
        return reviewService.getReviews();
    }

    @PostMapping("/review")
    public ResponseEntity<RespReview> addReview(@RequestBody InpReview review) {
        final RespReview respReview = reviewService.addReview(review);
        if (respReview != null) {
            return new ResponseEntity<>(respReview, HttpStatus.CREATED);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
