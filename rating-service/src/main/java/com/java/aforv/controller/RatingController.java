package com.java.aforv.controller;

import com.java.aforv.model.Rating;
import com.java.aforv.model.RatingRequest;
import com.java.aforv.model.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{name}")
    public ResponseEntity<Rating>getRating(@PathVariable String name) {
        Rating rating=ratingService.fetchRating(name);
        log.info("Returning rating for  movie:{}",name);
        return ResponseEntity.ok(rating);
    }

    @PostMapping()
    public ResponseEntity<Rating>updateRating(@RequestBody RatingRequest request) {
        Rating rating=ratingService.updateAverage(request.getName(), request.getStars());
        log.info("returning new Average for movie:{}", request.getName());
        return ResponseEntity.ok(rating);

    }
}
