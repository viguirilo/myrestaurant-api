package com.myrestaurant.api.controller;

import com.myrestaurant.api.entity.Restaurant;
import com.myrestaurant.api.service.RestaurantService;
import com.myrestaurant.api.vo.RestaurantRequestVO;
import com.myrestaurant.api.vo.RestaurantResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant save(@RequestBody RestaurantRequestVO restaurantRequestVO) {
        return restaurantService.save(restaurantRequestVO);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {
        return ResponseEntity.ok().body(restaurantService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestaurantResponseVO> findById(@PathVariable Long id) {
        RestaurantResponseVO restaurantResponseVO = restaurantService.findById(id);
        if (restaurantResponseVO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(restaurantResponseVO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable Long id, @RequestBody RestaurantRequestVO restaurantRequestVO) {
        Restaurant restaurant = restaurantService.update(id, restaurantRequestVO);
        if (restaurant != null) return ResponseEntity.ok().body(restaurant);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Restaurant restaurant = restaurantService.delete(id);
            if (restaurant == null) return ResponseEntity.notFound().build();
            else return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
