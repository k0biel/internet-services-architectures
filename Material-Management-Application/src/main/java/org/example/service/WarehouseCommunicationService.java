package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class WarehouseCommunicationService {

    private final RestTemplate restTemplate;

    @Autowired
    public WarehouseCommunicationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}