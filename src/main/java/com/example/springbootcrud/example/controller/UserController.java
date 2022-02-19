package com.example.springbootcrud.example.controller;

import com.example.springbootcrud.example.collections.ErrorSchema;
import com.example.springbootcrud.example.collections.NoDataResponse;
import com.example.springbootcrud.example.collections.Response;
import com.example.springbootcrud.example.constant.UserURL;
import com.example.springbootcrud.example.dto.request.UserRequest;
import com.example.springbootcrud.example.dto.response.UserResponse;
import com.example.springbootcrud.example.response.SuccessResponse;
import com.example.springbootcrud.example.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UserURL.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    @SneakyThrows
    public ResponseEntity<NoDataResponse<ErrorSchema>> submitForm(@Valid
                               @RequestBody UserRequest userRequest
                               )  {
        SuccessResponse<List<UserResponse>> response = new SuccessResponse<>();
        userService.saveUser(userRequest);

        return response.successPostUserData();
    }

    @GetMapping()
    public ResponseEntity<Response<ErrorSchema, List<UserResponse>>> showUser() {
        List<UserResponse> userList = userService.getAllUser();
        SuccessResponse<List<UserResponse>> response = new SuccessResponse<>();

        return response.successGetUserData(userList);
    }

    @GetMapping(UserURL.SEARCH)
    public ResponseEntity<Response<ErrorSchema, List<UserResponse>>> submitSearchUser(
                                                                            @RequestParam(name = "name") String name) {
        List<UserResponse> userList = userService.getUserByName(name);
        SuccessResponse<List<UserResponse>> response = new SuccessResponse<>();

        return response.successGetUserData(userList);
    }

}
