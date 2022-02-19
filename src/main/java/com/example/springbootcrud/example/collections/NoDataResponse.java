package com.example.springbootcrud.example.collections;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoDataResponse<T>{

    private T errorSchema;

}
