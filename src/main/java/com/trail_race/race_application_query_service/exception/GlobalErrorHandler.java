package com.trail_race.race_application_query_service.exception;


import com.trail_race.race_application_query_service.exception.dao.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalErrorHandler {

    @ResponseBody // 404
    @ExceptionHandler({
            NotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest req, Exception e) {
        return Responses.getAndLogDebug(e, e.getMessage(), req, HttpStatus.NOT_FOUND);
    }

    @ResponseBody  // 500
    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ErrorResponse> handleGenericExceptions(HttpServletRequest req, Exception e) {
        return Responses.getAndLogError(e, e.getMessage(), req, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
