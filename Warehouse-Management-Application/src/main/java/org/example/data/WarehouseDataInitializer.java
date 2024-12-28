package org.example.data;

import org.example.model.Warehouse;
import org.example.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WarehouseDataInitializer implements CommandLineRunner {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseDataInitializer(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void run(String... args) {
        UUID warehouse1Id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID warehouse2Id = UUID.fromString("22222222-2222-2222-2222-222222222222");

        Warehouse warehouse1 = Warehouse.builder()
                .id(warehouse1Id)
                .name("Warehouse A")
                .location("Warsaw")
                .build();

        Warehouse warehouse2 = Warehouse.builder()
                .id(warehouse2Id)
                .name("Warehouse B")
                .location("Gdansk")
                .build();

        warehouseRepository.save(warehouse1);
        warehouseRepository.save(warehouse2);
    }
}