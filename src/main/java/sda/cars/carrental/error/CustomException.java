package sda.cars.carrental.error;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomException() {
        super("Custom exception message.");
    }

    public CustomException(String excp) {
        super(excp);
    }

    @JsonGetter("errorInfo")
    public String getErrorMessage()
    {
        return super.getLocalizedMessage();
    }


}