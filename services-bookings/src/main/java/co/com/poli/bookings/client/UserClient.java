package co.com.poli.bookings.client;

import co.com.poli.bookings.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "services-users",fallback = UserClientFallBackHystrix.class)
public interface UserClient {
    @GetMapping("/users/{id}")
    public Response findById(@PathVariable("id") Long id);
}
