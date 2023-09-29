package org.operationsix.InventoryService.service;

import org.operationsix.InventoryService.models.Product;

import java.util.List;

public interface IinventoryService {
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
    public Product getProductByName(String productName) throws NoSuchFieldException;
    public Integer getQuantityByProductName(String productName);
    public List<Product> getAvailableProducts();
    public List<Product> getExpiredProducts();
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Boolean removeProduct(Long id) throws NoSuchFieldException;
}
