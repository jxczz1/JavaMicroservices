package co.com.poli.showtimes.client;

import co.com.poli.showtimes.model.Movie;
import co.com.poli.showtimes.utils.Response;
import co.com.poli.showtimes.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieClientFallBackHytrix implements MovieClient {

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
