package com.example.clothes_2nd.ApiExceptionHandler;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@ControllerAdvice
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler {
    //500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleInternalServerException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Server đang lỗi, vui lòng thử lại sau!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    //400
//    @ExceptionHandler(DataFormatException.class)
//    public ResponseEntity<?> dataInputException(DataFormatException ex, WebRequest request) {
//        Map<String, String> body = new HashMap<>();
//
//        body.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            @org.jetbrains.annotations.NotNull NoHandlerFoundException ex, @org.jetbrains.annotations.NotNull HttpHeaders headers, @org.jetbrains.annotations.NotNull HttpStatusCode status, @org.jetbrains.annotations.NotNull WebRequest request) {

//        String message = "Endpoint not found or method not allowed";
//        ErrorResponse errorResponse = new ErrorResponse(status.value(), message);

        return null;
    }

        //    @ExceptionHandler(DataFormatException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<String> handleDataFormatException(@org.jetbrains.annotations.NotNull DataFormatException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//    //401
//    @ExceptionHandler(BadCredentialsException.class)
//    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
//    public ErrorMessage handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
//        return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), "Sai thông tin đăng nhập!");
//    }
//    //403
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(value = HttpStatus.FORBIDDEN)
//    public ErrorMessage handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
//        return new ErrorMessage(HttpStatus.FORBIDDEN.value(), "Truy cập bị từ chối!");
//    }
//    //404
//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public ResponseEntity<String> handleNotFoundException(@NotNull ResourceNotFoundException ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//    //415
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//    public ErrorMessage handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, WebRequest request) {
//        return new ErrorMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "Kiểu phương tiện không được hỗ trợ!");
//    }
}