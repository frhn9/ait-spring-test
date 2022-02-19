package com.example.springbootcrud.example.service;

import com.example.springbootcrud.example.dto.request.AlamatRequest;
import com.example.springbootcrud.example.dto.request.UserRequest;
import com.example.springbootcrud.example.dto.response.UserResponse;
import com.example.springbootcrud.example.entity.Alamat;
import com.example.springbootcrud.example.entity.User;
import com.example.springbootcrud.example.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @MockBean
    UserRepository userRepository;

    User user = new User();
    List<User> userList = new ArrayList<>();
    Alamat alamat = new Alamat();
    List<Alamat> alamatList = new ArrayList<>();
    AlamatRequest alamatRequest = new AlamatRequest();

    List<UserResponse> userResponseList = new ArrayList<>();

    @BeforeEach
    void init(){
        user.setName("fadiel");
        user.setHp("082114578947");
        user.setEmail("fadhyl.frhn@gmail.com");

        alamat.setAlamat("Sebrang kantor AIT");

        alamatRequest.setAlamat(alamat.getAlamat());
    }

    @Test
    @SneakyThrows
    void user_shouldSave(){
        alamatList.add(modelMapper.map(alamatRequest, Alamat.class));

        when(userRepository.save(user)).thenReturn(user);
        userList.add(user);
        userService.saveUser(modelMapper.map(user, UserRequest.class));
        when(userRepository.findAll()).thenReturn(userList);

        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    @SneakyThrows
    void userShouldGetAll() {
        alamatList.add(alamat);

        userList.add(user);

        when(userRepository.save(user)).thenReturn(user);

        userResponseList.add(userService.saveUser(modelMapper.map(user, UserRequest.class)));

        when(userRepository.findAll()).thenReturn(userList);
        when(userService.getAllUser()).thenReturn(userResponseList);

        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    @SneakyThrows
    void userShouldGetByName(){
        alamatList.add(alamat);

        userList.add(user);
        when(userRepository.save(user)).thenReturn(user);

        userResponseList.add(userService.saveUser(modelMapper.map(user, UserRequest.class)));

        when(userRepository.findByNameIgnoreCaseContains("fadiel")).thenReturn(userList);
        when(userService.getUserByName("fadiel")).thenReturn(userResponseList);

        assertEquals(1, userRepository.findByNameIgnoreCaseContains("fadiel").size());
    }

    @Test
    void convertUserToUserResponse(){
        UserResponse userResponse = new UserResponse();
        userResponse = userService.convertUserToUserResponse(user);

        assertEquals(userService.convertUserToUserResponse(user).getAlamat(), userResponse.getAlamat());
    }

    @Test
    void isEmailDuplicate(){
        userList.add(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findByEmailIgnoreCase(user.getEmail())).thenReturn(userList);

        Boolean status = userService.isEmailDuplicate(user.getEmail());

        assertEquals(true, status);
    }

}