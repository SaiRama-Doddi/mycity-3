package com.example.hotels.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hotels.Model.Review;
import com.example.hotels.Service.ReviewService;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review")
    public Review saveReview(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }

    @PutMapping("/review")
    public Review updateReview(@RequestBody Review review) {
        return reviewService.updateReview(review);
    }

    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/review/{reviewId}")
    public Review getReviewById(@PathVariable Long reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @DeleteMapping("/review/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return "Review with id " + reviewId + " is deleted";
    }
}
