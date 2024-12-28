package org.example.controller;

import org.example.dto.WarehouseCreateUpdateDTO;
import org.example.dto.WarehouseDetailDTO;
import org.example.dto.WarehouseSummaryDTO;
import org.example.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<Void> createWarehouse(@RequestBody WarehouseCreateUpdateDTO warehouseDTO) {
        warehouseService.createWarehouse(warehouseDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDetailDTO> getWarehouseById(@PathVariable UUID id) {
        try {
            WarehouseDetailDTO warehouseDetail = warehouseService.getWarehouseById(id);
            return ResponseEntity.ok(warehouseDetail);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<WarehouseSummaryDTO>> getAllWarehouses() {
        List<WarehouseSummaryDTO> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWarehouse(
            @PathVariable UUID id,
            @RequestBody WarehouseCreateUpdateDTO warehouseDTO) {
        try {
            warehouseService.updateWarehouse(id, warehouseDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable UUID id) {
        boolean deleted = warehouseService.deleteWarehouse(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}