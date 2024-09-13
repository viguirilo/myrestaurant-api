package com.myrestaurant.api.vo;

import com.myrestaurant.api.entity.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StateResponseVO {

    public Long id;
    public String name;

    public StateResponseVO(State state) {
        this.id = state.getId();
        this.name = state.getName();
    }

}
