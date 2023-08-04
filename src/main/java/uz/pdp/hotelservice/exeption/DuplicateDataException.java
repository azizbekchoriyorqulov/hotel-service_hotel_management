package uz.pdp.hotelservice.exeption;

public class DuplicateDataException extends RuntimeException {
public DuplicateDataException(String message){
    super(message);
}
}
