package org.example.dto;

import org.example.model.Warehouse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@Builder
public class WarehouseSummaryDTO {
    private UUID id;
    private String name;

    public static WarehouseSummaryDTO from(Warehouse warehouse) {
        return WarehouseSummaryDTO.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .build();
    }
}