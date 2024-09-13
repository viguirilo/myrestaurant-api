package com.myrestaurant.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestVO {

    public String name;
    public BigDecimal shipRate;
    public Boolean active;
    public Boolean open;
    public LocalDateTime creationDate;
    public LocalDateTime updateDate;
    public Long kitchenId;
    public String addressStreetOrAvenue;
    public String addressNumber;
    public String addressComplement;
    public String addressNeighborhood;
    public String addressZipCode;
    public Long addressCityId;

}
