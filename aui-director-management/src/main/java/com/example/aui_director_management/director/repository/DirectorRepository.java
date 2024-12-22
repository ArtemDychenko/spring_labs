package com.example.aui_director_management.director.repository;

import com.example.aui_director_management.director.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DirectorRepository extends JpaRepository<Director, UUID> {
    List<Director> findByName(String name);}
