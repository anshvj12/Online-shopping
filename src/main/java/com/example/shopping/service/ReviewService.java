package com.example.shopping.service;

import com.example.shopping.Model.Review;
import com.example.shopping.request.InpReview;
import com.example.shopping.response.RespReview;

import java.util.List;

public interface ReviewService {

    RespReview addReview(InpReview review);

    List<Review> getReviews();
}
