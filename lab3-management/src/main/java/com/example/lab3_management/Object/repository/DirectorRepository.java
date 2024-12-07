package com.example.lab3_management.Object.repository;

import com.example.lab3_management.Object.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DirectorRepository extends JpaRepository<Director, UUID> {
    List<Director> findByName(String name);}
