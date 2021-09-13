package co.com.poli.showtimes.client;

import co.com.poli.showtimes.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "services-movies",fallback = MovieClientFallBackHytrix.class)
public interface MovieClient {
    @GetMapping("/movies/{id}")
    Response findById(@PathVariable("id") Long id);
}
