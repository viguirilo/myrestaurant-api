package com.myrestaurant.api.controller;

import com.myrestaurant.api.entity.Product;
import com.myrestaurant.api.service.ProductService;
import com.myrestaurant.api.vo.ProductRequestVO;
import com.myrestaurant.api.vo.ProductResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> save(@RequestBody ProductRequestVO permissionRequestVO) {
        Product product = productService.save(permissionRequestVO);
        if (product == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseVO> findById(@PathVariable Long id) {
        ProductResponseVO productResponseVO = productService.findById(id);
        if (productResponseVO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(productResponseVO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductRequestVO productRequestVO) {
        Product product = productService.update(id, productRequestVO);
        if (product != null) return ResponseEntity.ok().body(product);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Product product = productService.delete(id);
            if (product == null) return ResponseEntity.notFound().build();
            else return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

//    Aulas 4.33 e 4.34 ensinam como fazer o UPDATE parcial usando o PATCH.

}
