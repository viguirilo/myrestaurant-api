package com.myrestaurant.api.vo;

import com.myrestaurant.api.entity.City;
import com.myrestaurant.api.entity.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityResponseVO {

    public Long id;
    public String name;
    public State state;

    public CityResponseVO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.state = city.getState();
    }

}
