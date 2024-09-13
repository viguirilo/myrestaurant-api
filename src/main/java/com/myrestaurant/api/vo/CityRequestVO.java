package com.myrestaurant.api.vo;

import com.myrestaurant.api.entity.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityRequestVO {

    public String name;
    public State state;

}


