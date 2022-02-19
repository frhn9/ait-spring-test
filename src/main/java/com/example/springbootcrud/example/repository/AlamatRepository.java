package com.example.springbootcrud.example.repository;

import com.example.springbootcrud.example.entity.Alamat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlamatRepository extends JpaRepository<Alamat, String> {
}