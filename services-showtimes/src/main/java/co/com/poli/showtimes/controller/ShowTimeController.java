package co.com.poli.showtimes.controller;

import co.com.poli.showtimes.entities.ShowTime;
import co.com.poli.showtimes.services.ShowTimeService;
import co.com.poli.showtimes.utils.ErrorMessage;
import co.com.poli.showtimes.utils.Response;
import co.com.poli.showtimes.utils.ResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/showtimes")
@RequiredArgsConstructor
public class ShowTimeController {
    private final ShowTimeService showTimeService;
    private final ResponseBuilder builder;

    @PostMapping
    public Response save(@Valid @RequestBody ShowTime showTime, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage((result)));
        }
        showTimeService.save(showTime);
        return builder.success(showTime);
    }


    @GetMapping
    public ResponseEntity<List<ShowTime>> findAll(){
        List<ShowTime> movies = showTimeService.findAll();
        if(movies.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        ShowTime showTime = showTimeService.findById(id);
        return builder.success(showTime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        ShowTime showTime = showTimeService.findById(id);
        if(showTime==null){
            return ResponseEntity.notFound().build();
        }
        showTimeService.delete(showTime);
        return ResponseEntity.ok(showTime);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowTime> updateTutorial(@PathVariable("id") long id, @RequestBody ShowTime showTime) {
        ShowTime showTimeToUpdate = showTimeService.findById(id);
        if(showTimeToUpdate==null){
            return ResponseEntity.notFound().build();
        }
        showTimeService.update(showTime);
        return ResponseEntity.ok(showTime);

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
