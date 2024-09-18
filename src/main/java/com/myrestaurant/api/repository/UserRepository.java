package com.myrestaurant.api.repository;

import com.myrestaurant.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Rever aula 5.9 para ver como implementar queries personaalizadas usabdo JPQL
//    Rever aula 5.12 para ver como implementar queries dinâmicas usando JPQL
}
