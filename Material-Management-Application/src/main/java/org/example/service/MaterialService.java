package org.example.service;

import org.example.dto.MaterialCreateUpdateDTO;
import org.example.dto.MaterialDetailDTO;
import org.example.dto.MaterialSummaryDTO;
import org.example.model.Material;
import org.example.model.SimplifiedWarehouse;
import org.example.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final SimplifiedWarehouseService simplifiedWarehouseService;

    @Autowired
    public MaterialService(MaterialRepository materialRepository, SimplifiedWarehouseService simplifiedWarehouseService) {
        this.materialRepository = materialRepository;
        this.simplifiedWarehouseService = simplifiedWarehouseService;
    }

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Optional<Material> findById(UUID id) {
        return materialRepository.findById(id);
    }

    public void save(Material material) {
        materialRepository.save(material);
    }

    public void deleteById(UUID id) {
        materialRepository.deleteById(id);
    }

    public List<Material> findByWarehouse(UUID warehouseId) {
        SimplifiedWarehouse warehouse = simplifiedWarehouseService.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Simplified warehouse not found with id " + warehouseId));
        return materialRepository.findByWarehouse(warehouse);
    }

    public void createMaterial(UUID warehouseId, MaterialCreateUpdateDTO materialDTO) {
        SimplifiedWarehouse warehouse = simplifiedWarehouseService.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Simplified warehouse not found with id " + warehouseId));
        Material newMaterial = Material.builder()
                .id(UUID.randomUUID())
                .name(materialDTO.getName())
                .price(materialDTO.getPrice())
                .warehouse(warehouse)
                .build();
        materialRepository.save(newMaterial);
    }

    public MaterialDetailDTO getMaterialById(UUID id) {
        Material foundMaterial = findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id " + id));
        return MaterialDetailDTO.from(foundMaterial);
    }

    public List<MaterialSummaryDTO> getAllMaterials() {
        List<Material> materials = findAll();
        return materials.stream()
                .map(MaterialSummaryDTO::from)
                .collect(Collectors.toList());
    }

    public void updateMaterial(UUID id, MaterialCreateUpdateDTO materialDTO) {
        Material material = findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id " + id));
        material.setName(materialDTO.getName());
        material.setPrice(materialDTO.getPrice());
        save(material);
    }

    public boolean deleteMaterial(UUID id) {
        Optional<Material> material = findById(id);
        if (material.isPresent()) {
            deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void deleteMaterialsByWarehouseId(UUID warehouseId) {
        SimplifiedWarehouse warehouse = simplifiedWarehouseService.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Simplified warehouse not found with id " + warehouseId));
        List<Material> materials = materialRepository.findByWarehouse(warehouse);
        materials.forEach(material -> deleteById(material.getId()));
    }

    public List<Material> findMaterialsByWarehouseId(UUID warehouseId) {
        SimplifiedWarehouse warehouse = simplifiedWarehouseService.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Simplified warehouse not found with id " + warehouseId));
        return materialRepository.findByWarehouse(warehouse);
    }

}