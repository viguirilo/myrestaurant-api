package com.myrestaurant.api.service;

import com.myrestaurant.api.entity.Restaurant;
import com.myrestaurant.api.repository.RestaurantRepository;
import com.myrestaurant.api.vo.RestaurantRequestVO;
import com.myrestaurant.api.vo.RestaurantResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

//    Rever aula 4.30 para adequar como ele implementa esse método
    public Restaurant save(RestaurantRequestVO restaurantRequestVO) {
        return restaurantRepository.save(new Restaurant(restaurantRequestVO));
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public RestaurantResponseVO findById(Long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        return restaurantOptional.map(RestaurantResponseVO::new).orElse(null);
    }

    public Restaurant update(Long id, RestaurantRequestVO restaurantRequestVO) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            BeanUtils.copyProperties(restaurantRequestVO, restaurant, "id");
            return restaurantRepository.save(restaurant);
        } else return null;
    }

    public Restaurant delete(Long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            restaurantRepository.delete(restaurant);
            return restaurant;
        } else return null;
    }

}
