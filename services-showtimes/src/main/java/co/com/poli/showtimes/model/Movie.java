package co.com.poli.showtimes.model;

import lombok.Data;

@Data
public class Movie {
    private long id;
    private String title;
    private String director;
    private Integer rating;
}
