package com.huseynov.announcementbackend.advice;

import com.huseynov.announcementbackend.dto.BaseResponse;
import com.huseynov.announcementbackend.exception.ConflictException;
import com.huseynov.announcementbackend.exception.NotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage());

        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).body(baseResponse);
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException e) {
        log.error(e.getMessage());

        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status (HttpStatus.CONFLICT).body(baseResponse);

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e) {
        log.error(e.getMessage());

        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status (HttpStatus.UNAUTHORIZED).body(baseResponse);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage());

        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status (HttpStatus.NOT_FOUND).body(baseResponse);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        String message = String.join(",", errors);

        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(message);

        return ResponseEntity.status (HttpStatus.BAD_REQUEST)
                .body(baseResponse);
    }

}
