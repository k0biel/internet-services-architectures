package org.example.service;

import org.example.dto.WarehouseCreateUpdateDTO;
import org.example.dto.WarehouseDetailDTO;
import org.example.dto.WarehouseSummaryDTO;
import org.example.model.Warehouse;
import org.example.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final MaterialCommunicationService materialCommunicationService;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository, MaterialCommunicationService materialCommunicationService) {
        this.warehouseRepository = warehouseRepository;
        this.materialCommunicationService = materialCommunicationService;
    }

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> findById(UUID id) {
        return warehouseRepository.findById(id);
    }

    public void save(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    public void deleteById(UUID id) {
        warehouseRepository.deleteById(id);
    }

    public void createWarehouse(WarehouseCreateUpdateDTO warehouseDTO) {
        Warehouse warehouse = Warehouse.builder()
                .id(UUID.randomUUID())
                .name(warehouseDTO.getName())
                .location(warehouseDTO.getLocation())
                .build();
        materialCommunicationService.notifyMaterialsAboutNewWarehouse(warehouse);
        warehouseRepository.save(warehouse);
    }

    public WarehouseDetailDTO getWarehouseById(UUID id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id " + id));

        return WarehouseDetailDTO.from(warehouse);
    }

    public List<WarehouseSummaryDTO> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream()
                .map(WarehouseSummaryDTO::from)
                .collect(Collectors.toList());
    }

    public void updateWarehouse(UUID id, WarehouseCreateUpdateDTO warehouseDTO) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id " + id));

        warehouse.setName(warehouseDTO.getName());
        warehouse.setLocation(warehouseDTO.getLocation());
        materialCommunicationService.notifyMaterialsAboutUpdatedWarehouse(warehouse);
        warehouseRepository.save(warehouse);
    }

    public boolean deleteWarehouse(UUID warehouseId) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseId);

        if (warehouse.isPresent()) {
            materialCommunicationService.notifyMaterialsToDelete(warehouseId);
            warehouseRepository.deleteById(warehouseId);
            return true;
        }

        return false;
    }
}