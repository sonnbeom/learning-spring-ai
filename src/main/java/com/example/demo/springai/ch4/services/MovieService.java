package com.example.demo.springai.ch4.services;

import com.example.demo.springai.ch4.model.Movie;

import java.util.List;

public interface MovieService {

    Movie findMovie(String title);

    List<Movie> findMoviesByDirector(String director, int count);

    String askRawJsonTrap(String title);

    String askRawJsonFixed(String title);

    Movie describeFormat();
}
