package com.example.springbootcrud.example.service.implementation;

import com.example.springbootcrud.example.dto.request.AlamatRequest;
import com.example.springbootcrud.example.dto.request.UserRequest;
import com.example.springbootcrud.example.dto.response.UserResponse;
import com.example.springbootcrud.example.entity.Alamat;
import com.example.springbootcrud.example.entity.User;
import com.example.springbootcrud.example.repository.UserRepository;
import com.example.springbootcrud.example.service.UserService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @SneakyThrows
    public UserResponse saveUser(UserRequest userRequest) {
        List<Alamat> alamatList = new ArrayList<>();
        User user = modelMapper.map(userRequest, User.class);

        if(isEmailDuplicate(userRequest.getEmail())) throw new DuplicateName();

        for(AlamatRequest alamatRequest : userRequest.getAlamat()){
            Alamat alamat = modelMapper.map(alamatRequest, Alamat.class);
            alamat.setUser(user);
            alamatList.add(alamat);
        }
        user.setAlamat(alamatList);

        UserResponse userResponse = modelMapper.map(userRepository.save(user), UserResponse.class);

        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> userList = userRepository.findAll();
        if(userList.size() == 0) throw new NoSuchElementException();
        List<UserResponse> userResponseList = userList.stream().map(this::convertUserToUserResponse).collect(Collectors.toList());

        return userResponseList;
    }

    @Override
    public List<UserResponse> getUserByName(String name) {
        List<User> userList = userRepository.findByNameIgnoreCaseContains(name);
        if(userList.size() == 0) throw new NoSuchElementException();
        List<UserResponse> userResponseList = userList.stream().map(this::convertUserToUserResponse).collect(Collectors.toList());

        return userResponseList;
    }

    @Override
    public UserResponse convertUserToUserResponse(Object user){
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public Boolean isEmailDuplicate(String email){
        List<User> userList = userRepository.findByEmailIgnoreCase(email);

        if(userList.size() == 0){
            return false;
        }
        return true;
    }
}
