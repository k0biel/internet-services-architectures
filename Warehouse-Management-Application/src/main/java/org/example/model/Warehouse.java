package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "warehouses")
@AllArgsConstructor
public class Warehouse implements Serializable {

    @Id
    private UUID id;

    private String name;
    private String location;

    public Warehouse() {}

    @Override
    public String toString() {
        return "Warehouse(name=" + name + ", location=" + location + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse that = (Warehouse) o;
        return name.equals(that.name) &&
                location.equals(that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

}