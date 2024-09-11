package com.myrestaurant.api.service;

import com.myrestaurant.api.entity.PaymentMethod;
import com.myrestaurant.api.repository.PaymentMethodRepository;
import com.myrestaurant.api.vo.PaymentMethodResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodResponseVO findById(Long id) {
        Optional<PaymentMethod> paymentMethodById = paymentMethodRepository.findById(id);
        return paymentMethodById.map(PaymentMethodResponseVO::new).orElse(null);
    }

}
