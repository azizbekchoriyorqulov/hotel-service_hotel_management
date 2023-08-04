package uz.pdp.hotelservice.config;

import org.postgresql.util.PSQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.pdp.hotelservice.exeption.DataNotFoundException;
import uz.pdp.hotelservice.exeption.DuplicateDataException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<String> DataNotFoundExceptionHandler(
            DataNotFoundException e
    ) {
        return ResponseEntity.status(401).body(e.getMessage());
    }


    @ExceptionHandler(value = {DuplicateDataException.class})
    public ResponseEntity<String> requestValidationExceptionHandler(
            DuplicateDataException e
    ) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(value = {PSQLException.class})
    public ResponseEntity<String> PSQLExceptionHandler(
            PSQLException e
    ) {
        System.out.println("e.getMessage() = " + e.getMessage());

        return ResponseEntity.status(400).body(e.getLocalizedMessage());
    }


}
