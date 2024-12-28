package org.example.repository;

import org.example.model.Material;
import org.example.model.SimplifiedWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {
    List<Material> findByWarehouse(SimplifiedWarehouse warehouse);
    void deleteById(UUID id);
}