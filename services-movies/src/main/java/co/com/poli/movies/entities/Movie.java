package co.com.poli.movies.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.*;


@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "El titulo no puede ser vacio")
    @Column(name="title")
    private String title;

    @NotEmpty(message = "El director no puede ser vacio")
    @Column(name="director")
    private String director;
    
    @Min(1)
    @Max(5)
    private Integer rating;
}
