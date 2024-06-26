package soft.rodi38.eventorganizer.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import soft.rodi38.eventorganizer.exception.attendee.AttendeeNotFoundException;
import soft.rodi38.eventorganizer.exception.event.EventNotFoundException;
import soft.rodi38.eventorganizer.exception.organizer.OrganizerNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity<?> handleAttendeeNotFoundException(AttendeeNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<?> handleOrganizerNotFoundException(EventNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrganizerNotFoundException.class)
    public ResponseEntity<?> handleException(OrganizerNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
