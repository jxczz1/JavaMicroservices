package co.com.poli.users.utils;

import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Component
public class ResponseBuilder {

    public Response success(){
        return co.com.poli.users.utils.Response.builder()
                .data(OK)
                .status(OK.value())
                .build();
    }

    public Response success(Object data){
        return co.com.poli.users.utils.Response.builder()
                .data(data)
                .status(OK.value())
                .build();
    }

    public Response failed(Object data){
        return co.com.poli.users.utils.Response.builder()
                .data(data)
                .status(INTERNAL_SERVER_ERROR.value())
                .build();
    }
}
