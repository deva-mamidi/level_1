package com.deva.mamidi.movieinfoservice.controller;

import com.deva.mamidi.movieinfoservice.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @GetMapping("{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId) {
        return  new Movie(movieId, "Test name");

    }
}
