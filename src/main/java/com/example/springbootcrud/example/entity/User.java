package com.example.springbootcrud.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.NumberFormat;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Nama kosong")
    @Length(max = 50, message = "Nama diatas 50 karakter")
    private String name;

    @NotBlank(message = "No. Hp kosong")
    @Digits(integer = 10, fraction = 0, message = "Format No. Hp salah")
    private String hp;

    @NotBlank(message = "E-mail kosong")
    @Email(message =  "Format e-mail salah")
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @JsonIgnore
    @Valid
    private List<Alamat> alamat = new ArrayList<>();
}
