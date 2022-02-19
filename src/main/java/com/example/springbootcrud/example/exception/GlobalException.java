package com.example.springbootcrud.example.exception;

import com.example.springbootcrud.example.collections.ErrorSchema;
import com.example.springbootcrud.example.collections.NoDataResponse;
import com.example.springbootcrud.example.constant.Message;
import com.example.springbootcrud.example.constant.MessageCode;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalException {

    private NoDataResponse<ErrorSchema> response = new NoDataResponse<>();
    private ErrorSchema errorSchema = new ErrorSchema();

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<NoDataResponse<ErrorSchema>> handleValidation(ConstraintViolationException ex){
        String errorMessage = new ArrayList<>(ex.getConstraintViolations()).get(0).getMessage();

        errorSchema.setCode(MessageCode.ERROOR_VALIDATION_FAILED);
        errorSchema.setMessage(errorMessage);

        response.setErrorSchema(errorSchema);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<NoDataResponse<ErrorSchema>> handleNestedValidation(MethodArgumentNotValidException ex){
        errorSchema.setCode(MessageCode.ERROOR_VALIDATION_FAILED);
        errorSchema.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());

        response.setErrorSchema(errorSchema);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateName.class)
    public ResponseEntity<NoDataResponse<ErrorSchema>> handleDuplicateEmailValue(){
        errorSchema.setCode(MessageCode.ERROR_DATA_DUPLICATE);
        errorSchema.setMessage(Message.DATA_DUPLICATE);

        response.setErrorSchema(errorSchema);

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<NoDataResponse<ErrorSchema>> handleDataNotFound(){
        errorSchema.setCode(MessageCode.ERROR_DATA_NOT_FOUND);
        errorSchema.setMessage(Message.DATA_NOT_FOUND);

        response.setErrorSchema(errorSchema);

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

}
