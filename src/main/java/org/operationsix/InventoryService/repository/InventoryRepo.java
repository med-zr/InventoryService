package org.operationsix.InventoryService.repository;


import org.operationsix.InventoryService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Product,Long> {
    public Optional<Product> findByProductName(String productName);
}
