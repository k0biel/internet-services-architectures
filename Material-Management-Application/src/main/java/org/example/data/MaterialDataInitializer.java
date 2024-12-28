package org.example.data;

import org.example.model.Material;
import org.example.model.SimplifiedWarehouse;
import org.example.repository.MaterialRepository;
import org.example.repository.SimplifiedWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MaterialDataInitializer implements CommandLineRunner {

    private final MaterialRepository materialRepository;
    private final SimplifiedWarehouseRepository simplifiedWarehouseRepository;

    @Autowired
    public MaterialDataInitializer(MaterialRepository materialRepository, SimplifiedWarehouseRepository simplifiedWarehouseRepository) {
        this.materialRepository = materialRepository;
        this.simplifiedWarehouseRepository = simplifiedWarehouseRepository;
    }

    @Override
    public void run(String... args) {
        UUID warehouse1Id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID warehouse2Id = UUID.fromString("22222222-2222-2222-2222-222222222222");

        SimplifiedWarehouse warehouse1 = SimplifiedWarehouse.builder()
                .id(warehouse1Id)
                .name("Warehouse A")
                .build();

        SimplifiedWarehouse warehouse2 = SimplifiedWarehouse.builder()
                .id(warehouse2Id)
                .name("Warehouse B")
                .build();

        simplifiedWarehouseRepository.save(warehouse1);
        simplifiedWarehouseRepository.save(warehouse2);

        Material material1 = Material.builder()
                .id(UUID.randomUUID())
                .name("Wood")
                .price(50.0)
                .warehouse(warehouse1)
                .build();

        Material material2 = Material.builder()
                .id(UUID.randomUUID())
                .name("Steel")
                .price(3000.0)
                .warehouse(warehouse2)
                .build();

        materialRepository.save(material1);
        materialRepository.save(material2);
    }
}