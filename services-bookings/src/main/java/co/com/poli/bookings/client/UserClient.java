package co.com.poli.bookings.client;

import co.com.poli.bookings.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "services-users")
@RequestMapping(value="/users")
public interface UserClient {
    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id);
}
