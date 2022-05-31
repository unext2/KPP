package com.example.demo.exception;

import com.example.demo.WeekDayController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {
    private Logger logger = LoggerFactory.getLogger(WeekDayController.class);
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleException1(){
        logger.warn("Get request without param");
        return new ResponseEntity<>("Empty param sended",HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleException2(){
        logger.warn("Get param with mistake");
        return new ResponseEntity<>("Bad name",HttpStatus.BAD_REQUEST);

    }
}
