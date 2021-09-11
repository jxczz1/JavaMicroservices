package co.com.poli.bookings.services;

import co.com.poli.bookings.entities.Booking;
import co.com.poli.bookings.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;

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
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(long id) {
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
