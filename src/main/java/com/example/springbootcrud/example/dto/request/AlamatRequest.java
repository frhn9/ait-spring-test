package com.example.springbootcrud.example.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AlamatRequest {
    @NotBlank(message = "Alamat tidak boleh kosong")
    private String alamat;
}
