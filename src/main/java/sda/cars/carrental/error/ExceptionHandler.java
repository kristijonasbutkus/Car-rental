package sda.cars.carrental.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public CustomException handleCustomException(CustomException ce) {
        return ce;
    }

}