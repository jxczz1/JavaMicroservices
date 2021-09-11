package co.com.poli.bookings.services;

import co.com.poli.bookings.entities.Booking;

import java.util.List;

public interface BookingService {
    void save(Booking booking);
    void delete(Booking booking);
    List<Booking> findAll();
    Booking findById(long id);
    Booking findByUserId(long userId);
    Booking update(Booking booking);
}
