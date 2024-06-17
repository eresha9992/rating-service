package com.java.aforv.model.service;

import com.java.aforv.RatingServiceApplication;
import com.java.aforv.exception.NotFoundException;
import com.java.aforv.model.Rating;
import com.java.aforv.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating updateAverage(String name,double starts){
        Rating rating=ratingRepository.findByName(name);
        if(rating==null) {
            rating=new Rating();
            rating.setName(name);
            rating.setAvgRating(starts);
            rating.setCount(1);
        }else {
            int count=  rating.getCount();
            double newAverage=(rating.getAvgRating()* count +
                    starts)/(count+1);
            rating.setAvgRating(newAverage);
            rating.setCount(++count);
        }
        return ratingRepository.save(rating);

    }
    public Rating fetchRating(String name) {
        Rating rating=ratingRepository.findByName(name);
        if(rating==null) {
            throw new NotFoundException("movie not found with name"+name);
        }
        return rating;
    }
}
