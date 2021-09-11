package co.com.poli.bookings.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name="user_id", nullable = false)
    private long userId;

    @NonNull
    @Column(name="showtime_id", nullable = false)
    private long showTimeId;

    private ArrayList<Long> movies = new ArrayList<Long>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
