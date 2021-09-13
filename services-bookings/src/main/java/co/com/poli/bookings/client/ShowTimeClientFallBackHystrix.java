package co.com.poli.bookings.client;

import co.com.poli.bookings.model.ShowTime;
import co.com.poli.bookings.utils.Response;
import co.com.poli.bookings.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowTimeClientFallBackHystrix implements  ShowTimeClient {
    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        ShowTime showTime = new ShowTime();
        return  builder.success(showTime);
    }
}
