package com.example.springbootcrud.example.dto.response;

import com.example.springbootcrud.example.entity.Alamat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String hp;
    private String email;
    private List<Alamat> alamat;
}
