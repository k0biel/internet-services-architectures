package org.example.service;

import org.example.dto.WarehouseSummaryDTO;
import org.example.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class MaterialCommunicationService {

    private final RestTemplate restTemplate;

    @Autowired
    public MaterialCommunicationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void notifyMaterialsToDelete(UUID warehouseId) {
        restTemplate.delete("http://material-application:8082/materials/warehouse/" + warehouseId);
    }

    public void notifyMaterialsAboutNewWarehouse(Warehouse warehouse) {
        String url = "http://material-application:8082/materials/warehouse/" + warehouse.getId();
        WarehouseSummaryDTO dto = WarehouseSummaryDTO.from(warehouse);
        restTemplate.postForObject(url, dto, Void.class);
    }

    public void notifyMaterialsAboutUpdatedWarehouse(Warehouse warehouse) {
        String url = "http://material-application:8082/materials/warehouse/" + warehouse.getId();
        WarehouseSummaryDTO dto = WarehouseSummaryDTO.from(warehouse);
        restTemplate.put(url, dto);
    }
}
