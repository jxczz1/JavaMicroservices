package co.com.poli.bookings.services;

import co.com.poli.bookings.client.MovieClient;
import co.com.poli.bookings.client.ShowTimeClient;
import co.com.poli.bookings.client.UserClient;
import co.com.poli.bookings.entities.Booking;
import co.com.poli.bookings.model.Movie;
import co.com.poli.bookings.model.ShowTime;
import co.com.poli.bookings.model.User;
import co.com.poli.bookings.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final MovieClient movieClient;
    private final UserClient userClient;
    private final ShowTimeClient showTimeClient;

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void delete(Booking booking) {
     bookingRepository.delete(booking);
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookingList = bookingRepository.findAll();
        List<Booking> bookingsResponse =  bookingList.stream().map( booking -> {
            ModelMapper modelMapper = new ModelMapper();
            List<Movie> moviesList = booking.getMovies().stream()
                    .map(movieId -> {
                        Movie movie = modelMapper.map(movieClient.findById(movieId).getData(), Movie.class);
                        return movie;
                    }).collect(Collectors.toList());
            booking.setMoviesInfo(moviesList);
            User user  = modelMapper.map(userClient.findById(booking.getUserId()).getData(),User.class);
            booking.setUserInfo(user);
            ShowTime showTime  = modelMapper.map(showTimeClient.findById(booking.getShowTimeId()).getData(),ShowTime.class);
            booking.setShowTime(showTime);
            return booking;
        }).collect(Collectors.toList());
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(long id) {
       Booking booking =  bookingRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        List<Movie> moviesList = booking.getMovies().stream()
                .map( movieId -> {
                    Movie movie = modelMapper.map(movieClient.findById(movieId).getData(),Movie.class);
                    return movie;
                }).collect(Collectors.toList());
        booking.setMoviesInfo(moviesList);
        User user  = modelMapper.map(userClient.findById(booking.getUserId()).getData(),User.class);
        booking.setUserInfo(user);
        ShowTime showTime  = modelMapper.map(showTimeClient.findById(booking.getShowTimeId()).getData(),ShowTime.class);
        booking.setShowTime(showTime);
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking findByUserId(long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepository.save(booking);
    }

}
