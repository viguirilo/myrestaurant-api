package com.myrestaurant.api.repository;

import com.myrestaurant.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    Rever aula 5.9 para ver como implementar queries personaalizadas usabdo JPQL
//    Rever aula 5.12 para ver como implementar queries dinâmicas usando JPQL
}
