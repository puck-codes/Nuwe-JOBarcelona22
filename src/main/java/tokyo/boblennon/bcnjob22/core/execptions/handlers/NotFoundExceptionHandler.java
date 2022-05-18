package tokyo.boblennon.bcnjob22.core.execptions.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import tokyo.boblennon.bcnjob22.core.execptions.NotFoundException;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(value = { NotFoundException.class })
    protected ResponseEntity<Object> handleConflict(NotFoundException ex, WebRequest request) {
        return ResponseEntity.status(ex.getCode()).body("");
    }
}