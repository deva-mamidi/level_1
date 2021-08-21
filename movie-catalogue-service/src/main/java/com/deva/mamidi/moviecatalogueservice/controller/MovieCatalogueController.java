package com.deva.mamidi.moviecatalogueservice.controller;

import com.deva.mamidi.moviecatalogueservice.model.CatalogueItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogueController {

    @GetMapping("{userId}")
    public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId){
        return Collections.singletonList(
                new CatalogueItem("Transformers", "movie", 4)
        );
    }
}
