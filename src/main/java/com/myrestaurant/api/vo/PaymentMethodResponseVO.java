package com.myrestaurant.api.vo;

import com.myrestaurant.api.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentMethodResponseVO {

    public Long id;

    public String description;

    public PaymentMethodResponseVO(PaymentMethod paymentMethod) {
        this.id = paymentMethod.getId();
        this.description = paymentMethod.getDescription();
    }
}
