package sda.cars.carrental.error;

public class RangeValidationException extends CustomException {

    public RangeValidationException(String excp) {
        super(excp);
    }

    public RangeValidationException( Exception e ) {
        super(e.getMessage());
    }
}
