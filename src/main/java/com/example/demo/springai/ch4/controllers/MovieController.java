package com.example.demo.springai.ch4.controllers;

import com.example.demo.springai.ch4.model.Movie;
import com.example.demo.springai.ch4.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/one")
    public Movie one(@RequestParam(defaultValue = "기생충") String title){
        return movieService.findMovie(title);
    }

    @GetMapping("/by-director")
    public List<Movie> byDirector(@RequestParam(defaultValue = "봉준호") String director,
                                  @RequestParam(defaultValue = "3") int count){
        return movieService.findMoviesByDirector(director, count);
    }

    @GetMapping("/raw-json-trap")
    public String rasJsonTrap (@RequestParam(defaultValue = "기생충") String title){
        return movieService.askRawJsonTrap(title);
    }

    @GetMapping("/raw-json-fixed")
    public String rasJsonFixed (@RequestParam(defaultValue = "기생충") String title){
        return movieService.askRawJsonFixed(title);
    }

    @GetMapping("/format")
    public Movie format(){
        return movieService.describeFormat();
    }


}
