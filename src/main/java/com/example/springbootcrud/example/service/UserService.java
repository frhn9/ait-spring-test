package com.example.springbootcrud.example.service;

import com.example.springbootcrud.example.dto.request.UserRequest;
import com.example.springbootcrud.example.dto.response.UserResponse;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserRequest userRequest) throws DuplicateName;
    List<UserResponse> getAllUser();
    List<UserResponse> getUserByName(String name);

    UserResponse convertUserToUserResponse(Object user);
    Boolean isEmailDuplicate(String email);
}
