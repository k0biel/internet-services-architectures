package org.example.dto;

import org.example.model.Warehouse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class WarehouseDetailDTO {
    private UUID id;
    private String name;
    private String location;

    public static WarehouseDetailDTO from(Warehouse warehouse) {
        return WarehouseDetailDTO.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .location(warehouse.getLocation())
                .build();
    }
}