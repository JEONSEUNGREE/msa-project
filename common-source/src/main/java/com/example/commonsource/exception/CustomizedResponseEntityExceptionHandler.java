package com.example.commonsource.exception;

import com.example.commonsource.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.Date;


public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.
                builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // UserNotFound Exception
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.
                builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Validation Exception
    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.
                builder()
                .message("Validation Failed")
                .timestamp(new Date())
                .details(ex.getBindingResult().toString())
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    // UserNotFound Exception
    @ExceptionHandler(JwtBadCredentialsException.class)
    public final ResponseEntity<Object> jwtBadCredentialException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.
                builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    // ApiException
    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<Object> apiException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.
                builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    // NoOrderException
    @ExceptionHandler(NoOrderResult.class)
    public final ResponseEntity<Object> noOrderResult(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.
                builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    // NoOrderException
    @ExceptionHandler(OrderProcessingException.class)
    public final ResponseEntity<Object> orderProcessingException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.
                builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }



    // NoOrderException
    @ExceptionHandler(NoProductResult.class)
    public final ResponseEntity<Object> noOrderProductResultException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.
                builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NO_CONTENT);
    }
}
