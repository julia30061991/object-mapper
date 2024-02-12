package com.object.mapper.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @Column(name = "product_id", columnDefinition = "INT", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;
    @Column(name = "description", columnDefinition = "VARCHAR(255)")
    private String description;
    @Column(name = "price", columnDefinition = "NUMERIC")
    private double price;
    @Column(name = "quantityInStock", columnDefinition = "INT")
    private int quantityInStock;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                "quantityInStock='" + quantityInStock + '\'' +
                '}';
    }
}