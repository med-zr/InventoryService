package org.operationsix.InventoryService.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.operationsix.InventoryService.models.Product;
import org.operationsix.InventoryService.service.InventoryServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "InventoryController", description = "Controleur de la gestion du stock")
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    private final InventoryServiceImp inventoryService;
    private final String productUrl = "/products";
    @GetMapping(productUrl)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){
        return inventoryService.getAllProducts();
    }
    @GetMapping(productUrl+"/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAvailableProducts(){
        return inventoryService.getAvailableProducts();
    }
    @GetMapping(productUrl+"/expired")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getExpiredProducts(){
        return inventoryService.getExpiredProducts();
    }
    @GetMapping(productUrl+"/productName")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductByName(@RequestParam(name = "productName") String productName) throws NoSuchFieldException{
        return inventoryService.getProductByName(productName);
    }
    @PostMapping("/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return inventoryService.createProduct(product);
    }
    @PutMapping("/updateProduct")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody Product product){
        return inventoryService.updateProduct(product);
    }

    @DeleteMapping("/removeProduct")
    public ResponseEntity<String> removeProduct(@RequestParam(name = "id") Long id) throws NoSuchFieldException{
        return inventoryService.removeProduct(id)? ResponseEntity.ok("Product successfully deleted"):ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error in deletion : Product not found");
    }

}
