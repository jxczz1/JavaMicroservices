package co.com.poli.bookings.client;

import co.com.poli.bookings.model.Movie;
import co.com.poli.bookings.utils.Response;
import co.com.poli.bookings.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieClientFallBackHystrix implements  MovieClient {
    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        Movie movie = new Movie();
        movie.setTitle("no hay informacion");
        movie.setDirector("no hay informacion");
        movie.setRating(1);
        return  builder.success(movie);
    }
}
