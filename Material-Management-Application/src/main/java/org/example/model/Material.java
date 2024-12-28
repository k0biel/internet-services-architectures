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
@Table(name = "materials")
@AllArgsConstructor
public class Material implements Serializable {

    @Id
    private UUID id;

    private String name;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private SimplifiedWarehouse warehouse;

    public Material() {}

    @Override
    public String toString() {
        return "Material(name=" + name + ", price=" + price + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return name.equals(material.name) &&
                price == material.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}