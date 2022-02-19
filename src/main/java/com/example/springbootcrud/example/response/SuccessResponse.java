package com.example.springbootcrud.example.response;

import com.example.springbootcrud.example.collections.ErrorSchema;
import com.example.springbootcrud.example.collections.NoDataResponse;
import com.example.springbootcrud.example.collections.Response;
import com.example.springbootcrud.example.constant.Message;
import com.example.springbootcrud.example.constant.MessageCode;
import com.example.springbootcrud.example.dto.request.UserRequest;
import com.example.springbootcrud.example.dto.response.UserResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class SuccessResponse<T> {

    public ResponseEntity<Response<ErrorSchema, List<UserResponse>>> successGetUserData
            (T data) {
        Response<ErrorSchema, List<UserResponse>> response = new Response<>();
        ErrorSchema errorSchema = new ErrorSchema();
        List<UserResponse> userResponse = (List<UserResponse>) data;

        errorSchema.setCode(MessageCode.SUCCESS);
        errorSchema.setMessage(Message.SUCCESS);

        response.setErrorSchema(errorSchema);
        response.setData(userResponse);

        return ResponseEntity.status(HttpStatus.OK.value()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    public ResponseEntity<NoDataResponse<ErrorSchema>> successPostUserData() {
        NoDataResponse<ErrorSchema> response = new NoDataResponse<>();
        ErrorSchema errorSchema = new ErrorSchema();

        errorSchema.setCode(MessageCode.SUCCESS);
        errorSchema.setMessage(Message.SUCCESS);

        response.setErrorSchema(errorSchema);

        return ResponseEntity.status(HttpStatus.CREATED.value()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

}
