package com.myrestaurant.api.vo;

import com.myrestaurant.api.entity.City;
import com.myrestaurant.api.entity.Kitchen;
import com.myrestaurant.api.entity.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KitchenResponseVO {

    public Long id;
    public String name;
    public State state;

    public KitchenResponseVO(Kitchen kitchen) {
        this.id = kitchen.getId();
        this.name = kitchen.getName();
    }

    public KitchenResponseVO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.state = city.getState();
    }

}
