package com.deva.mamidi.ratingsdataservice.controller;

import com.deva.mamidi.ratingsdataservice.model.Rating;
import com.deva.mamidi.ratingsdataservice.model.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("ratings")
public class RatingsDataController {

    @GetMapping("{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 5);
    }


    @GetMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        return new UserRating( Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        ));
    }
}
