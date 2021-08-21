package com.deva.mamidi.moviecatalogueservice.controller;

import com.deva.mamidi.moviecatalogueservice.model.CatalogueItem;
import com.deva.mamidi.moviecatalogueservice.model.Movie;
import com.deva.mamidi.moviecatalogueservice.model.Rating;
import com.deva.mamidi.moviecatalogueservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogueController {
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("{userId}")
    public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId){

        //Step -1 create mock data
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 3)
//        );

        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratings/users/" + userId, UserRating.class);

        //Step -2 map through ratings and call movie api with rest template
        return ratings.getRatings().stream().map(
                rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
//                    Movie movie = builder.build()
//                            .get().uri("http://movie-info-service/movies/"+ rating.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class)
//                            .block();

                    return new CatalogueItem(movie.getName(), "Test", rating.getRating());
                }
        ).collect(Collectors.toList());

    }
}
