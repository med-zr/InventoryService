package org.operationsix.InventoryService.models;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ProductTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    @Column(length = 50,nullable = false,unique = true)
    private String productName;
    @Column(nullable = false,columnDefinition = "integer default 1")
    private int quantity;
    @Column(nullable=false)
    private double price;
    @Column(nullable = false)
    private String imageUrl;
}
