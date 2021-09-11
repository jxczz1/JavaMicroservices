package co.com.poli.bookings.controller;

import co.com.poli.bookings.entities.Booking;
import co.com.poli.bookings.services.BookingService;
import co.com.poli.bookings.utils.ErrorMessage;
import co.com.poli.bookings.utils.Response;
import co.com.poli.bookings.utils.ResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final ResponseBuilder builder;

    @PostMapping
    public Response save(@Valid @RequestBody Booking booking, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage((result)));
        }
        bookingService.save(booking);
        return builder.success(booking);
    }


    @GetMapping
    public ResponseEntity<List<Booking>> findAll(){
        List<Booking> bookings = bookingService.findAll();
        if(bookings.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        Booking booking = bookingService.findById(id);
        return builder.success(booking);
    }

    @GetMapping("/userId/{id}")
    public Response findByUserId(@PathVariable("id") Long id){
        Booking booking = bookingService.findByUserId(id);
        return builder.success(booking);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        Booking booking = bookingService.findById(id);
        if(booking==null){
            return ResponseEntity.notFound().build();
        }
        bookingService.delete(booking);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateTutorial(@PathVariable("id") long id, @RequestBody Booking booking) {
        Booking bookingToUpdate = bookingService.findById(id);
        if(bookingToUpdate==null){
            return ResponseEntity.notFound().build();
        }
        bookingService.update(booking);
        return ResponseEntity.ok(booking);
    }


    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try{
            json = objectMapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return json;
    }
}
