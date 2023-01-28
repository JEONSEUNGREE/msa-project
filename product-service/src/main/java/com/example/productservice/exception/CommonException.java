package com.example.productservice.exception;

import com.example.commonsource.exception.CustomizedResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ControllerAdvice
public class CommonException extends CustomizedResponseEntityExceptionHandler {


}
