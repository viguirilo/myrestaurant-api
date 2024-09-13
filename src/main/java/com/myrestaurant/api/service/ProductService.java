package com.myrestaurant.api.service;

import com.myrestaurant.api.entity.Product;
import com.myrestaurant.api.entity.Restaurant;
import com.myrestaurant.api.repository.ProductRepository;
import com.myrestaurant.api.repository.RestaurantRepository;
import com.myrestaurant.api.vo.ProductRequestVO;
import com.myrestaurant.api.vo.ProductResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RestaurantRepository restaurantRepository;

    public Product save(ProductRequestVO productRequestVO) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(productRequestVO.getRestaurantId());
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            return productRepository.save(new Product(productRequestVO, restaurant));
        } else return null;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ProductResponseVO findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.map(ProductResponseVO::new).orElse(null);
    }

    public Product update(Long id, ProductRequestVO productRequestVO) {
        Optional<Product> productOptional = productRepository.findById(id);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(productRequestVO.getRestaurantId());
        if (productOptional.isPresent() && restaurantOptional.isPresent()) {
            Product product = productOptional.get();
            Restaurant restaurant = restaurantOptional.get();
            BeanUtils.copyProperties(productRequestVO, product, "id", "restaurant");
            product.setRestaurant(restaurant);
            return productRepository.save(product);
        } else return null;
    }

    public Product delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.delete(product);
            return product;
        } else return null;
    }

}
