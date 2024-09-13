package com.myrestaurant.api.vo;

import com.myrestaurant.api.entity.Kitchen;
import com.myrestaurant.api.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantResponseVO {

    public Long id;
    public String name;
    public BigDecimal shipRate;
    public Boolean active;
    public Boolean open;
    public LocalDateTime creationDate;
    public LocalDateTime updateDate;
    public Kitchen kitchen;

    public RestaurantResponseVO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.shipRate = restaurant.getShipRate();
        this.active = restaurant.getActive();
        this.open = restaurant.getOpen();
        this.creationDate = restaurant.getCreationDate();
        this.updateDate = restaurant.getUpdateDate();
        this.kitchen = restaurant.getKitchen();
    }

}
