package com.myrestaurant.api.controller;

import com.myrestaurant.api.service.PaymentMethodService;
import com.myrestaurant.api.vo.PaymentMethodResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentMethodResponseVO> findById(@PathVariable Long id) {
        PaymentMethodResponseVO paymentMethodResponseVO = paymentMethodService.findById(id);
        if (paymentMethodResponseVO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(paymentMethodResponseVO);
    }

}
