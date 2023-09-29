package org.operationsix.InventoryService.service;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.operationsix.InventoryService.models.Product;
import org.operationsix.InventoryService.repository.InventoryRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImp implements IinventoryService{
    private final InventoryRepo inventoryRepo;
    @Override
    public List<Product> getAllProducts() {
        return inventoryRepo.findAll().stream()
                .sorted(Comparator.comparingLong(Product::getIdProduct))
                .collect(Collectors.toList());
    }
    @Override
    public Product getProductById(Long id) {
        if(inventoryRepo.findById(id).isPresent()) {
            return inventoryRepo.findById(id).get();
        }else{
            log.info("Product does not exist in the warehouse");
            return null;
        }
    }
    @Override
    public Product getProductByName(String productName) throws NoSuchFieldException{
        if(inventoryRepo.findByProductName(productName).isPresent()){
            return inventoryRepo.findByProductName(productName).get();
        }else{
            throw new NoSuchFieldException();
        }

    }
    @Override
    public Integer getQuantityByProductName(String productName) {
        if(inventoryRepo.findByProductName(productName).isPresent()) return inventoryRepo.findByProductName(productName).get().getQuantity();
        else{
            log.info("Product does not exist in the warehouse");
            return null;
        }
    }
    @Override
    public List<Product> getAvailableProducts(){
        return inventoryRepo.findAll().stream()
                .filter(product -> product.getQuantity()>0)
                .sorted(Comparator.comparingLong(Product::getIdProduct))
                .collect(Collectors.toList());
    };
    @Override
    public List<Product> getExpiredProducts(){
        return inventoryRepo.findAll().stream()
                .filter(product -> product.getQuantity()==0)
                .sorted(Comparator.comparingLong(Product::getIdProduct))
                .collect(Collectors.toList());
    };
    @Override
    public Product createProduct(Product product) {
        if((inventoryRepo.findByProductName(product.getProductName()).isPresent()
                && inventoryRepo.findByProductName(product.getProductName()).get().getQuantity()==0)){
            product.setIdProduct(inventoryRepo.findByProductName(product.getProductName()).get().getIdProduct());
        }
        if (product.getQuantity()==0) product.setQuantity(1);
        return inventoryRepo.save(product);
    }
    @Override
    public Product updateProduct(Product product) {
        if(inventoryRepo.findByProductName(product.getProductName()).isPresent()){
            product.setIdProduct(inventoryRepo.findByProductName(product.getProductName()).get().getIdProduct());
        }
        return inventoryRepo.save(product);
    }
    @Override
    public Boolean removeProduct(Long id) throws NoSuchFieldException{
        if(inventoryRepo.findById(id).isPresent()){
            Product product = inventoryRepo.findById(id).get();
            product.setQuantity(0);
            inventoryRepo.save(product);
            return true;
        }else{
            log.info("Product does not exist in the warehouse");
            return false;
        }
    }


}
