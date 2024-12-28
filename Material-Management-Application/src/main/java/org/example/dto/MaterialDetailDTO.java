package org.example.dto;

import org.example.model.Material;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class MaterialDetailDTO {
    private UUID id;
    private String name;
    private double price;
    private UUID warehouseId;

    public static MaterialDetailDTO from(Material material) {
        return MaterialDetailDTO.builder()
                .id(material.getId())
                .name(material.getName())
                .price(material.getPrice())
                .warehouseId(material.getWarehouse().getId())
                .build();
    }
}