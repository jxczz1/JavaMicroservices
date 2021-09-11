package co.com.poli.showtimes.services;

import co.com.poli.showtimes.client.MovieClient;
import co.com.poli.showtimes.entities.ShowTime;
import co.com.poli.showtimes.model.Movie;
import co.com.poli.showtimes.repositories.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {
    private final ShowTimeRepository showTimeRepository;
    private final MovieClient movieClient;

    @Override
    public void save(ShowTime showTime) {
        showTimeRepository.save(showTime);
    }

    @Override
    public void delete(ShowTime showTime) {
        showTimeRepository.delete(showTime);
    }

    @Override
    public List<ShowTime> findAll() {
        return showTimeRepository.findAll();
    }

    @Override
    public ShowTime findById(long id) {
        ShowTime showTime = showTimeRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        List<Movie> moviesList = showTime.getMovies().stream()
                .map( movieId -> {
                    Movie movie = modelMapper.map(movieClient.findById(movieId).getData(),Movie.class);
                    return movie;
                }).collect(Collectors.toList());
        showTime.setMoviesInfo(moviesList);
        return showTimeRepository.findById(id).orElse(null);
    }

    @Override
    public ShowTime update(ShowTime showTime) {
      return showTimeRepository.save(showTime);
    }
}
