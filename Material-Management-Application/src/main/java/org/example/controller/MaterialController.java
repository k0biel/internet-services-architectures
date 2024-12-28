package org.example.controller;

import org.example.dto.MaterialCreateUpdateDTO;
import org.example.dto.MaterialDetailDTO;
import org.example.dto.MaterialSummaryDTO;
import org.example.model.Material;
import org.example.model.SimplifiedWarehouse;
import org.example.service.MaterialService;
import org.example.service.SimplifiedWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materials")
public class MaterialController {

    private final MaterialService materialService;
    private final SimplifiedWarehouseService simplifiedWarehouseService;

    @Autowired
    public MaterialController(MaterialService materialService, SimplifiedWarehouseService simplifiedWarehouseService) {
        this.materialService = materialService;
        this.simplifiedWarehouseService = simplifiedWarehouseService;
    }

    @PostMapping("/{warehouseId}")
    public ResponseEntity<Void> createMaterial(
            @PathVariable UUID warehouseId,
            @RequestBody MaterialCreateUpdateDTO materialDTO) {
        try {
            materialService.createMaterial(warehouseId, materialDTO);
            return ResponseEntity.status(201).build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDetailDTO> getMaterialById(@PathVariable UUID id) {
        try {
            MaterialDetailDTO materialDetailDTO = materialService.getMaterialById(id);
            return ResponseEntity.ok(materialDetailDTO);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MaterialSummaryDTO>> getAllMaterials() {
        List<MaterialSummaryDTO> materials = materialService.getAllMaterials();
        return ResponseEntity.ok(materials);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMaterial(
            @PathVariable UUID id,
            @RequestBody MaterialCreateUpdateDTO materialDTO) {
        try {
            materialService.updateMaterial(id, materialDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable UUID id) {
        boolean deleted = materialService.deleteMaterial(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/warehouse/{warehouseId}")
    public ResponseEntity<Void> deleteMaterialsByWarehouseId(@PathVariable UUID warehouseId) {
        materialService.deleteMaterialsByWarehouseId(warehouseId);
        simplifiedWarehouseService.deleteSimplifiedWarehouse(warehouseId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/warehouse/{warehouseId}")
    public ResponseEntity<Void> addSimplifiedWarehouse(
            @PathVariable UUID warehouseId,
            @RequestBody SimplifiedWarehouse warehouseDTO) {
        try {
            simplifiedWarehouseService.createSimplifiedWarehouse(warehouseId, warehouseDTO.getName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/warehouse/{warehouseId}")
    public ResponseEntity<Void> updateSimplifiedWarehouse(
            @PathVariable UUID warehouseId,
            @RequestBody SimplifiedWarehouse warehouseDTO) {
        try {
            simplifiedWarehouseService.updateSimplifiedWarehouse(warehouseId, warehouseDTO.getName());
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<MaterialSummaryDTO>> getMaterialsByWarehouseId(@PathVariable UUID warehouseId) {
        try {
            List<Material> materials = materialService.findMaterialsByWarehouseId(warehouseId);
            List<MaterialSummaryDTO> materialDTOs = materials.stream()
                    .map(MaterialSummaryDTO::from)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(materialDTOs);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}