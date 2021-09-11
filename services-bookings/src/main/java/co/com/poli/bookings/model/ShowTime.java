package co.com.poli.bookings.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class ShowTime {
    private long id;
    private Date date;
    private ArrayList<Long> movies = new ArrayList<Long>();
}
