package org.example.controller;

import org.example.model.SimplifiedWarehouse;
import org.example.service.SimplifiedWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class SimplifiedWarehouseController {

    private final SimplifiedWarehouseService simplifiedWarehouseService;

    @Autowired
    public SimplifiedWarehouseController(SimplifiedWarehouseService simplifiedWarehouseService) {
        this.simplifiedWarehouseService = simplifiedWarehouseService;
    }

    @GetMapping
    public ResponseEntity<List<SimplifiedWarehouse>> getAllWarehouses() {
        List<SimplifiedWarehouse> warehouses = simplifiedWarehouseService.findAll();
        return ResponseEntity.ok(warehouses);
    }
}