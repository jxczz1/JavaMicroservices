package co.com.poli.showtimes.entities;

import co.com.poli.showtimes.model.Movie;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "showtimes")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name="date")
    private Date date;

    private ArrayList<Long> movies = new ArrayList<Long>();

    @Transient
    private List<Movie> moviesInfo;;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowTime showTime = (ShowTime) o;
        return id == showTime.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
