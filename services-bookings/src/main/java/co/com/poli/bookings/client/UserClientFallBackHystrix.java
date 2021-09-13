package co.com.poli.bookings.client;

import co.com.poli.bookings.model.User;
import co.com.poli.bookings.utils.Response;
import co.com.poli.bookings.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClientFallBackHystrix implements UserClient {
    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        User user = new User();
        user.setName("no tiene");
        user.setLastName("no tiene");
        return  builder.success(user);
    }
}
