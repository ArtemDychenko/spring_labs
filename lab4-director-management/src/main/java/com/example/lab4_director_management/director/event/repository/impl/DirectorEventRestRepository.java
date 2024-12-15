package com.example.lab4_director_management.director.event.repository.impl;

import com.example.lab4_director_management.director.event.repository.api.DirectorEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class DirectorEventRestRepository implements DirectorEventRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public DirectorEventRestRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void deleteDirector(UUID id) {
        restTemplate.delete("/api/directors/{id}", id);
    }
}
