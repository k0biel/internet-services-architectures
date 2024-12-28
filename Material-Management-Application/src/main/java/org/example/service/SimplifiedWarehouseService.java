package org.example.service;

import org.example.model.SimplifiedWarehouse;
import org.example.repository.SimplifiedWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SimplifiedWarehouseService {

    private final SimplifiedWarehouseRepository simplifiedWarehouseRepository;

    @Autowired
    public SimplifiedWarehouseService(SimplifiedWarehouseRepository repository) {
        this.simplifiedWarehouseRepository = repository;
    }

    public List<SimplifiedWarehouse> findAll() {
        return simplifiedWarehouseRepository.findAll();
    }

    public void createSimplifiedWarehouse(UUID id, String name) {
        SimplifiedWarehouse warehouse = new SimplifiedWarehouse(id, name);
        simplifiedWarehouseRepository.save(warehouse);
    }

    public void deleteSimplifiedWarehouse(UUID id) {
        simplifiedWarehouseRepository.deleteById(id);
    }

    public Optional<SimplifiedWarehouse> findById(UUID id) {
        return simplifiedWarehouseRepository.findById(id);
    }

    public void updateSimplifiedWarehouse(UUID id, String name) {
        SimplifiedWarehouse warehouse = simplifiedWarehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Simplified warehouse not found with id " + id));
        warehouse.setName(name);
        simplifiedWarehouseRepository.save(warehouse);
    }
}