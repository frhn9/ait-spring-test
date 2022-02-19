package com.example.springbootcrud.example.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String hp;
    private String email;
    @Valid
    private List<AlamatRequest> alamat;
}
