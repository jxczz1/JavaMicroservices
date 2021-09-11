package co.com.poli.movies.services;

import co.com.poli.movies.entities.Movie;

import java.util.List;

public interface MovieService {
    void save(Movie movie);
    void delete(Movie movie);
    List<Movie> findAll();
    Movie findById(long id);
}
